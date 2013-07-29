package com.kmware.automation.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 1:58
 */
public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox"),
    MSIE("msie"),
    OPERA("opera"),
    SAFARI("safari");

    public final String value;

    Browsers(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Browsers getBy(String val){
        Browsers[] browsers = Browsers.values();
        for (Browsers browser : browsers) {
            if(browser.value.equals(val+"")){
                return browser;
            }
        }
        return null;
    }
}
