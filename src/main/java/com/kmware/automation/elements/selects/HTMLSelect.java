package com.kmware.automation.elements.selects;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.ISelect;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 15:31
 */
@JQElement(
        extraSelector = "select"
)
public class HTMLSelect extends Element implements ISelect<HTMLSelect> {
    public HTMLSelect(jQuery j) {
        super(j);
    }

    public HTMLSelect(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public HTMLSelect(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public HTMLSelect(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public HTMLSelect(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public HTMLSelect(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }

    @Override
    public HTMLSelect expand() {
        this.nclick();
        return this;
    }

    @Override
    public HTMLSelect selectAt(int index) {
        this.find("option:eq("+index+")").get().click();
        return this;
    }

    @Override
    public HTMLSelect selectValue(String value) {
        this.find("option:contains("+value+")").get().click();
        return null;
    }

    @Override
    public HTMLSelect selectBy(String criteria) {
        this.find("option"+criteria+"").get().click();
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Element items() {
        return this.find("option").as(Element.class);
    }

    @Override
    public Element itemsWith(String value) {
        return this.find("option:contains("+value+")").as(Element.class);
    }

    @Override
    public Element items(String criteria) {
        return this.find("option"+criteria).as(Element.class);
    }

    @Override
    public String selectedText() {
        return this.find("option:selected").val();
    }

    @Override
    public String selectedValue() {
        return this.val();
    }
}
