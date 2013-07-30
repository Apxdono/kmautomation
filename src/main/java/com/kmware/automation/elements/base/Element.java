package com.kmware.automation.elements.base;

import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;
import com.kmware.automation.reflection.utils.ReflectUtils;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 13:33
 */
public class Element<T extends Element> extends jQuery {
    public Element(jQuery j) {
        super(j);
    }

    public Element(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public Element(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public Element(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public Element(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public Element(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }

    public T nclick(){
        jsref(".clickEventDispatch();");
        return (T) this;
    }

    /**
     * Comfortable method for ajax wait
     * @see com.kmware.automation.jquery.jQuery#wait4ajax()
     * @return
     */
    public T ajaxWait(){
        wait4ajax();
        return (T) this;
    }

    public T getEl(){
        return getEl(0);
    }

    public T getEl(int index){
        T el = (T) ReflectUtils.getAs(this.jget(index), getClass());
        return el;
    }

}
