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
 * Created with IntelliJ IDEA.<br/>
 * User: Oleg<br/>
 * Date: 26.07.13 1:53<br/>
 * The main feature of a framework. Initializes the webdriver and other required features.<br/>
 *
 * @since 1.0 tested on browsers chrome 28+, IE 9+, firefox 9.0+, opera 11.60 (build 1185), safari 5.1.7 (windows)<br/>
 *        Not sure if Safari will be fully supported for now. For details see bug https://code.google.com/p/selenium/issues/detail?id=4996
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

    /**
     * Just create a browser.properties file in the resources and go staight to work.
     * This static method will just init the Browser instance which you can access anywhere.
     *
     * @return initialized Browser instance
     */
    public static Browser browser() {
        return SingletonHolder.instance;
    }

    /**
     * Uses 'browser.properties' file in your classpath to init a new Browser insance
     */
    public Browser() {
        options = new PropertiesHelper();
        driver = null;
        current = null;
        init();
    }

    /**
     * For multiple Browser insances use this constructor to init from a different properties file;
     *
     * @param propertyFile
     */
    public Browser(String propertyFile) {
        options = new PropertiesHelper();
        driver = null;
        current = null;
        init(propertyFile);
    }

    /**
     * @return current browser implementation
     */
    public Browsers current() {
        return current;
    }

    /**
     * @return initialized WebDriver (Actually an instance of RemoteWebDriver)
     */
    public WebDriver driver() {
        return this.driver;
    }

    /**
     * Shorthand for (JavascriptExecutor) driver
     *
     * @return a casted tp JE driver instance
     */
    public JavascriptExecutor js() {
        return this.jExec;
    }

    /**
     * @return jQuery Factory class for this browser
     */
    public jQueryFactory jq() {
        return this.jquery;
    }

    /**
     * If a navigation with a pageload will be performed when a particular element is clicked
     * use this method which helps to do it correctly (There are a couple of bugs with webdriver not waiting for page to load)
     * Else it's up to you to handle the bugs.
     *
     * @param selector element to click
     * @return the clicked element. Just in case if you need it for some other stuff to do. Or to reuse in future see jQuery revive method
     */
    public Element navigate(String selector) {
        return navigate(selector, 0);
    }

    /**
     * This method is for convenience if for some reason you can't uniquely define the selector for an element.
     * (and just happens that you don't know about :eq pseudo)
     *
     * @param selector non unique css selector for element
     * @param index    will handle the uniqueness
     * @return well you know already
     * @see Browser#navigate(String)
     */
    public Element navigate(String selector, int index) {
        documentReady();
        Element e = this.jq().query(selector, Element.class).getEl(index);
        String url = driver().getCurrentUrl();
        e.as(Element.class).nclick();
        while (url.equals(driver().getCurrentUrl())) {
            log.debug("Waiting for page to load");
            goToSleep(1000);
        }
        documentReady();
        return e;
    }

    /**
     * CM Punk's finisher move ;) <br></br>
     * Just kidding. This is just a wrapper for Thread.sleep
     * @see Thread#sleep method
     * @param ms time in milliseconds
     */
    public void goToSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Wasn't able to perform Thread.sleep");
        }
    }

    /**
     * Check of document DOM is loaded
     * @return true if loaded. false otherwise.
     */
    public boolean documentReady() {
        while (true) {
            Boolean b = (Boolean) js().executeScript("return document.readyState === \"complete\"");
            log.debug("Document ready? {}", b.booleanValue());
            if (b.booleanValue()) break;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * shorthand method
     */
    protected void init() {
        this.init("browser.properties");
    }

    /**
     * The magic begins as soon as you provide a valid propertyfile in classpath.
     * PLEASE NOTE. You don't need to call the WebDriver quit or close method. It will be called
     * automatically as soon as the app finishes working.
     * @param propertyFile
     */
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
