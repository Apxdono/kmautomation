package com.kmware.automation.elements.listbox;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IListBoxSelect;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PFOneListBox.SELECTOR)
public class PFOneListBox extends Element<PFOneListBox> implements IListBoxSelect<PFOneListBox> {
	public static final String SELECTOR = ".ui-selectonelistbox";
	public static final String ITEMS_SELECTOR = "div[id$=\"%s\"] .ui-selectlistbox-list .ui-selectlistbox-item";

	public PFOneListBox(jQuery j) {
		super(j);
	} 

	public PFOneListBox(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFOneListBox(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFOneListBox(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFOneListBox(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFOneListBox(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

	@Override
	public PFOneListBox selectAt(int index) {
		String id = this.attr("id");
		this.jqf.query(String.format(ITEMS_SELECTOR, id)).jget(index).as(Element.class).nclick();
		return this;
	}

	@Override
	public PFOneListBox selectValue(String value) {
		items(":contains(" + value + ")").click();
        return this;
	}

	@Override
	public PFOneListBox selectBy(String criteria) {
		items(criteria).click();
		return this;
	}

	@Override
	public Element items() {
		items("");
		return this;
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
