package com.kmware.automation.actions;

import com.kmware.automation.browser.Browser;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 30.07.13 20:17
 */
@Action(
        id = PrimefacesGoToMainAction.ID
)
public class PrimefacesGoToMainAction implements IAction {

    public static final String ID = "pf_goto_main";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void init() {

    }

    @Override
    public void run(Browser browser,Object... args) {
        browser.driver().get("http://primefaces.org");
        browser.navigate(".button-block > a",0);
    }
}
