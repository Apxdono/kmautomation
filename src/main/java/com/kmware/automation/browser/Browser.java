package com.kmware.automation.browser;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.enums.Browsers;
import com.kmware.automation.enums.Options;
import com.kmware.automation.io.utils.PropertiesHelper;
import com.kmware.automation.jquery.jQueryFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 1:53
 * Not sure if Safari will be fully supported for now. For details see bug https://code.google.com/p/selenium/issues/detail?id=4996
 */
public class Browser {

    protected PropertiesHelper options;
    protected WebDriver driver;
    protected JavascriptExecutor jExec;
    protected TakesScreenshot screener;
    protected jQueryFactory jquery;

    protected Browsers current;

    protected Logger log = LoggerFactory.getLogger(Browser.class);

    private static class SingletonHolder {
        private static final Browser instance = new Browser();
    }

    public static Browser browser() {
        return SingletonHolder.instance;
    }


    public Browser() {
        options = new PropertiesHelper();
        driver = null;
        current = null;
        init();
    }

    public Browser(String propertyFile) {
        options = new PropertiesHelper();
        driver = null;
        current = null;
        init(propertyFile);
    }

    public Browsers current() {
        return current;
    }

    public WebDriver driver() {
        return this.driver;
    }

    public JavascriptExecutor js() {
        return this.jExec;
    }

    public jQueryFactory jq() {
        return this.jquery;
    }

    public Element navigate(String selector) {
        return navigate(selector, 0);
    }

    public Element navigate(String selector, int index) {
        documentReady();
        Element e = this.jq().query(selector, Element.class).getEl(index);
        String url = driver().getCurrentUrl();
        System.out.println(url);
        e.as(Element.class).nclick();
        while (url.equals(driver().getCurrentUrl())){
            System.out.println("Avaiting page load");
            goToSleep(1000);
        }
        documentReady();
        return e;
    }

    /**
     * CM Punk's finisher move ;)
     * @param ms
     */
    public void goToSleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Wasn't able to perform Thread.sleep");
        }
    }

    public boolean documentReady() {
        while (true) {
            Boolean b = (Boolean) js().executeScript("return document.readyState === \"complete\"");
            log.info("Document ready? {}", b.booleanValue());
            if (b.booleanValue()) break;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    protected void init() {
        this.init("browser.properties");
    }

    protected void init(String propertyFile) {
        log.info("Creating new Browser instance.");
        options.load(propertyFile, true);
        current = Browsers.getBy(options.property(Options.DRIVER_IMPLEMENTATION, Options.DRIVER_IMPLEMENTATION.defaults));
        log.info("Selected driver implementation: {}", current.value);
        String url = options.property(Options.DRIVER_HUB, Options.DRIVER_HUB.defaults);
        URL hub = null;
        try {
            hub = new URL(url);
        } catch (MalformedURLException e) {
            log.error("Bad hub url provided: {}", url);
            log.error("Aborting execution. REASON: ", e);
        }
        if (hub == null)
            throw new RuntimeException("Cannot find remote driver hub at url: " + url + ". Please fix the problem.");
        log.info("Driver hub found at: {}", url);
        driver = new RemoteWebDriver(hub, getDesiredCapabilities());
        driver.manage().timeouts();
        jExec = (JavascriptExecutor) driver;
        screener = (TakesScreenshot) new Augmenter().augment(driver);
        jquery = new jQueryFactory();
        jquery.setJs(jExec);
        jquery.setBrowserImplementation(current);
        final WebDriver drvr = driver;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Closing driver");
                try {
                    drvr.quit();
                } catch (Throwable t) {
                    log.error("Error during closing driver. Details below:", t);
                }
            }
        });


    }

    protected DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities result = null;
        switch (current) {
            case FIREFOX:
                result = DesiredCapabilities.firefox();
                break;
            case MSIE:
                result = DesiredCapabilities.internetExplorer();
                break;
            case OPERA:
                result = DesiredCapabilities.opera();
                break;
            case SAFARI:
                result = DesiredCapabilities.safari();
                break;
            default:
                result = DesiredCapabilities.chrome();
                break;
        }
        result.setJavascriptEnabled(true);
        String platform = options.property(Options.PLATFORM, Options.PLATFORM.defaults);
        result.setPlatform(Platform.extractFromSysProperty(platform));
        return result;
    }
}
