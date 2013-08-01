package com.kmware.automation.elements.selects;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IListBoxSelect;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PFManyCheckBox.SELECTOR)
public class PFManyCheckBox extends Element<PFManyCheckBox> implements IListBoxSelect<PFManyCheckBox> {
	public static final String SELECTOR = ".ui-selectmanycheckbox";
	public static final String ITEMS_SELECTOR = "table[id$=\"%s\"] .ui-chkbox .ui-chkbox-box";
	private int selectedNumber;

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
		selectedNumber = index;
		String id = this.attr("id");
		this.jqf.query(String.format(ITEMS_SELECTOR, id)).jget(index).as(Element.class).click();
		return this;
	}

	@Override
	public PFManyCheckBox selectValue(String value) {
		items(":contains(" + value + ")").click();
        return this;
	}

	@Override
	public PFManyCheckBox selectBy(String criteria) {
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
	//	$('.ui-selectmanycheckbox:eq(0)').find('.ui-chkbox:eq(0)').find('.ui-helper-hidden-accessible').find('input').val();
		String id = this.attr("id");
		String val = this.jqf.query(String.format(ITEMS_SELECTOR, id)).jget(selectedNumber).parent().parent().next().find("label").text();
		
		return val;
	}

	@Override
	public String selectedValue() {
		String id = this.attr("id");
		String val = this.jqf.query(String.format(ITEMS_SELECTOR, id)).jget(selectedNumber).parent().find(".ui-helper-hidden-accessible").find("input").val();
		
		return val;
	}
}
