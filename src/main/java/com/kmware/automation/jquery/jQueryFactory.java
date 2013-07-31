package com.kmware.automation.jquery;

import com.kmware.automation.enums.Browsers;
import com.kmware.automation.reflection.utils.ReflectUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

public class jQueryFactory {

    private String ref = "SeleniumjQuery";
    private String url = "jquery-1.7.1.js";
    private JavascriptExecutor js;
    private long defaultTimeout = 30000L;
    private final AtomicLong id_factory = new AtomicLong();
    private Browsers impl;
    private Logger log = LoggerFactory.getLogger(jQueryFactory.class);

    public long getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setBrowserImplementation(Browsers i) {
        this.impl = i;
    }

    public void setDefaultTimeout(long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

    public jQueryFactory(JavascriptExecutor js) {
        this.js = js;
    }

    public jQueryFactory() {
    }

    public void setJs(JavascriptExecutor js) {
        this.js = js;
    }

    public void checkReady() {
    }


    public long count(String query) {
        return query(query).length();
    }

    public jQuery querySafe(String query) {
        jQuery q = query(query);
        if (q.length() == 0) {
            throw new NoSuchElementException(query);
        }
        return q;
    }

    public jQuery queryUntilAtLeast(String query, int min) throws TimeoutException {
        return queryUntilAtLeast(query, min, getDefaultTimeout());
    }

    public jQuery queryUntil(String query) throws TimeoutException {
        return queryUntilAtLeast(query, 1, getDefaultTimeout());
    }

    public jQuery queryUntil(String query, long timeout) throws TimeoutException {
        return queryUntilAtLeast(query, 1, timeout);
    }

    public jQuery queryUntilAtLeast(String query, int min, long timeout) throws TimeoutException {
        return queryUntil(query, min, -1, timeout);
    }

    public jQuery queryUntilAtMost(String query, int max, long timeout) throws TimeoutException {
        return queryUntil(query, 0, max, timeout);
    }

    public jQuery queryUntilAtMost(String query, int max) throws TimeoutException {
        return queryUntil(query, 0, max, getDefaultTimeout());
    }

    public jQuery queryUntilNone(String query, long timeout) throws TimeoutException {
        return queryUntil(query, 0, 0, timeout);
    }

    public jQuery queryUntilNone(String query) throws TimeoutException {
        return queryUntil(query, 0, 0, getDefaultTimeout());
    }

    public jQuery queryUntil(String query, int min, int max, long timeout) throws TimeoutException {
        final jQuery q = query(query);
        q.setTimeout(timeout);
        try {
            while (true) {
                if (q.length() >= min) {
                    if (max == -1 || q.length() <= max) {
                        return q;
                    }
                }
                q.pause("Looking for " + query + " with " + min + " to " + max + " results.");
                q.init();
            }
        } finally {
            q.clearTimeout();
        }
    }

    /**
     * Query using css selector and return a jquery instance that represents found elements
     * @param query css selector
     * @return jQuery object
     */
    public jQuery query(String query) {
        return new jQuery(this, createId(), query);
    }

    /**
     * Similar to query(selector) method but with small sugar.
     * First you can specify the return element type either than jQuery.
     * Also this gives the ability to use the selector formatting. use %s in your selector to apply
     * additional selector of an element class you are passing in.
     * for eg. selector 'form %s:visible' will transform to 'form button:visible' for a simple Button
     * element or 'form .ui-button:visible' for.
     * primefaces Button if passed as a second argument.
     * But if you provide a direct selector to an element you select like '#buttonId' then it's still ok.
     * The method will simply return the element if found and cast it to required Class
     * @param query css selector optionally w/ formatting
     * @param klazz the return object class
     * @param <E>
     * @return the object you are looking for
     */
    public <E> E query(String query, Class<E> klazz) {
        String selector = query;
        if(query.contains("%s")) selector = String.format(query, ReflectUtils.getExtraSelector(klazz));
        return ReflectUtils.getAs(this, createId(), selector, klazz);
    }


    public jQuery query(WebElement we) {
        this.checkReady();
        return new jQuery(this, createId(), we);
    }

    /**
     * Use this mehtod preferably cause it injects jquery to the page if it's not yet injected
     * @see JavascriptExecutor#executeScript(String, Object...)
     * @param script
     * @param args
     * @return
     */
    public Object js(String script, Object... args) {
        if (js == null) {
            throw new IllegalStateException("Cannot run js without setting the js executor!");
        }
        try {
            log.debug("Executing script: {} . Arguments: {}",script,args);
            return js.executeScript(script, args);
        } catch (Exception e) {
            try {
                if (ensurejQuery()) {
                    return js(script, args);
                }
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public JavascriptExecutor getJs(){
        return this.js;
    }

    public jQueryFactory setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean ensurejQuery() throws IOException {
        if ((Boolean) js.executeScript("return typeof window[arguments[0]] != typeof __undefined", getRef())) {
            return false;
        }
        InputStream is = getClass().getClassLoader().getResourceAsStream(getUrl());
        js.executeScript(IOUtils.readFully(is));
        is.close();
        js.executeScript("window[arguments[0]] = jQuery.noConflict(true)", getRef());
        String errorHandlingScript =  "window.collectedErrors = [];"
                + "window.onerror = function(errorMessage) { "
                + "window.collectedErrors[window.collectedErrors.length] = errorMessage;"
                + "}";
        js.executeScript(errorHandlingScript);
        return true;
    }

    public long createId() {
        return id_factory.incrementAndGet();
    }

    public jQueryFactory setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String getRef() {
        return ref;
    }
}
