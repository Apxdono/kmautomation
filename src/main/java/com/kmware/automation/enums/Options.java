package com.kmware.automation.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 1:54
 */
public enum Options {
    DRIVER_HUB("driver.hub","http://127.0.0.1:4444/wd/hub"),
    DRIVER_IMPLEMENTATION("driver.implementation",Browsers.CHROME.value),
    PLATFORM("desired.platform","WIN"),
    SCREENSHOT_DIR("screenshot.dir","screenshots"),
    PACKAGES("actions.packages",""),
    STARTUP_ACTIONS("startup.actions","");

    public final String value;
    public final String defaults;
    Options(String value, String d) {
        this.value = value;
        this.defaults = d;
    }

    @Override
    public String toString() {
        return value;
    }
}
