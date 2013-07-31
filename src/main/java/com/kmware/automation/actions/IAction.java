package com.kmware.automation.actions;

import com.kmware.automation.browser.Browser;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 1:51
 */
public interface IAction {
    public String getId();
    public void init(Browser browser);
    public void run(Browser browser,Object... args);
}
