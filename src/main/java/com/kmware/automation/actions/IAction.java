package com.kmware.automation.actions;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 1:51
 */
public interface IAction {
    public String getId();
    public void init();
    public void run();
}
