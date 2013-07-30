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
 * Date: 26.07.13 16:25
 */
@JQElement(
        extraSelector = PFOneMenu.SELECTOR
)
public class PFOneMenu extends Element<PFOneMenu> implements ISelect<PFOneMenu>{
    public static final String SELECTOR = ".ui-selectonemenu";
    public static final String BUTTON_SELECTOR = ".ui-selectonemenu-trigger";
    public static final String ITEMS_SELECTOR = "div[id$=\"%s_panel\"].ui-selectonemenu-panel .ui-selectonemenu-item";
    public PFOneMenu(jQuery j) {
        super(j);
    }

    public PFOneMenu(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public PFOneMenu(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public PFOneMenu(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public PFOneMenu(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public PFOneMenu(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }

    @Override
    public PFOneMenu expand() {
        this.find(BUTTON_SELECTOR).jget().as(Element.class).nclick();
        return this;
    }

    @Override
    public PFOneMenu selectAt(int index) {
        String id = this.attr("id");
        this.jqf.query(String.format(ITEMS_SELECTOR,id)).jget(index).as(Element.class).nclick();
        return this;
    }

    @Override
    public PFOneMenu selectValue(String value) {
        items(":contains(" + value + ")").click();
        return this;
    }

    @Override
    public PFOneMenu selectBy(String criteria) {
        items(criteria).click();
        return this;
    }

    @Override
    public Element items() {
        return items("");
    }

    @Override
    public Element itemsWith(String value) {
        return items(":contains(" + value + ")");
    }

    @Override
    public Element items(String criteria) {
        String id = this.attr("id");
        return this.jqf.query(String.format(ITEMS_SELECTOR+criteria,id),Element.class);
    }

    @Override
    public String selectedText() {
        return this.find("select :selected").text();
    }

    @Override
    public String selectedValue() {
        return this.find("select").val();
    }
}
