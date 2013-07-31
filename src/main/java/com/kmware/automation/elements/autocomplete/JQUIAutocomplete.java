package com.kmware.automation.elements.autocomplete;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IAutocomplete;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 30.07.13 23:02
 */
@JQElement(
        extraSelector = ".ui-autocomplete-input"
)
public class JQUIAutocomplete extends Element<JQUIAutocomplete> implements IAutocomplete<JQUIAutocomplete> {


    public JQUIAutocomplete(jQuery j) {
        super(j);
    }

    public JQUIAutocomplete(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public JQUIAutocomplete(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public JQUIAutocomplete(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public JQUIAutocomplete(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public JQUIAutocomplete(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }

    @Override
    public JQUIAutocomplete val(String val) {
        super.val(val);
        return this;
    }

    @Override
    public JQUIAutocomplete expand() {
        jqf.getJs().executeScript("$(arguments[0]).autocomplete('search');",this.cssPath());
        wait4ajax();
        return this;
    }

    @Override
    public JQUIAutocomplete selectAt(int index) {
        items(String.format(":eq(%d)", index)).getEl().nclick();
        return this;
    }

    @Override
    public JQUIAutocomplete selectValue(String value) {
        itemsWith(value).getEl().nclick();
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JQUIAutocomplete selectBy(String criteria) {
        items(criteria).getEl().nclick();
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Element items() {
        return items("");
    }

    @Override
    public Element itemsWith(String value) {
        return items(String.format(":contains('%s')", value));
    }

    @Override
    public Element items(String criteria) {
        String menu = (String) jqf.js(String.format("return $(arguments[0]).autocomplete('widget').attr('id')"),this.cssPath());
        Element e = jqf.query("#"+menu).find("li"+criteria).as(Element.class);
        return e;
//        return subset(String.format(".autocomplete('widget').find('li%s');", criteria)).as(Element.class);
    }

    @Override
    public String selectedText() {
        return this.val();
    }

    @Override
    public String selectedValue() {
        return this.val();
    }
}
