package com.kmware.automation.elements.checkboxmenu;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IListBoxSelect;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PFManyCheckBox.SELECTOR)
public class PFManyCheckBox extends Element<PFManyCheckBox> implements IListBoxSelect<PFManyCheckBox> {
	public static final String SELECTOR = ".ui-selectmanycheckbox>tbody";
	public static final String ITEMS_SELECTOR = ".ui-chkbox";
	public static final String ITEMS_CLICK_SELECTOR = ".ui-chkbox-box";

	public PFManyCheckBox(jQuery j) {
		super(j);
	} 

	public PFManyCheckBox(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFManyCheckBox(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFManyCheckBox(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFManyCheckBox(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFManyCheckBox(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

	@Override
	public PFManyCheckBox selectAt(int index) {
		items().jget(index).find(ITEMS_CLICK_SELECTOR).click();
		return this;
	}

	@Override
	public PFManyCheckBox selectValue(String value) {
		itemsWith(value).find(ITEMS_CLICK_SELECTOR).click();
        return this;
	}

	@Override
	public PFManyCheckBox selectBy(String criteria) {
		items(criteria).find(ITEMS_CLICK_SELECTOR).click();
		return this;
	}

	@Override
	public Element items() {
		return items("");
	}

	@Override
	public Element itemsWith(String value) {
		String crit = String.format(":has(input[value*=\"%s\"])",value);
		return items(crit);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Element items(final String criteria) {				
		Element e= this.find(ITEMS_SELECTOR+criteria).as(Element.class);
		return e;
	}

	@Override
	public String selectedText() {
		String id = this.attr("id");
		String val = this.jqf.query(ITEMS_SELECTOR+":has(input:checked)").parent().next().find("label").text();
		return val;
	}

	@Override
	public String selectedValue() {
		String id = this.attr("id");
		String val = this.jqf.query(ITEMS_SELECTOR+":has(input:checked)").find(".ui-helper-hidden-accessible").find("input").val();
		return val;
	}
}
