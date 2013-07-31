package com.kmware.automation.elements.selects;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IListBoxSelect;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PFOneRadio.SELECTOR)
public class PFOneRadio extends Element<PFOneRadio> implements IListBoxSelect<PFOneRadio> {
	public static final String SELECTOR = ".ui-selectoneradio";
	public static String ITEMS_SELECTOR = "table[id$=\"%s\"] .ui-radiobutton .ui-radiobutton-box";
	public static final String CHECK_ITEMS_SELECTOR = "table[id$=\"%s\"] .ui-radiobutton .ui-state-active";
	
	public PFOneRadio(jQuery j) {
		super(j);
	} 

	public PFOneRadio(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFOneRadio(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFOneRadio(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFOneRadio(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFOneRadio(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

	@Override
	public PFOneRadio selectAt(int index) {
		String id = this.attr("id");
		this.jqf.query(String.format(ITEMS_SELECTOR, id)).jget(index).as(Element.class).nclick();
		return this;
	}
	
	@Override
	public PFOneRadio selectValue(String value) {
		String id = this.attr("id");
		this.jqf.query(String.format(ITEMS_SELECTOR, id)).parent().parent().next().find("label:contains("+value+")").parent().prev().find(".ui-radiobutton-box").click();
        return this;
	}

	@Override
	public PFOneRadio selectBy(String criteria) {
		items(criteria).click();
		return this;
	}

	@Override
	public Element items() {
		return items("");
	}

	@Override
	public Element itemsWith(String value) {
		items(":eq(" + value + ")").click();
		return this;
	}

	@Override
	public Element items(String criteria) {
		 /*String id = this.attr("id");
		 Element q= this.jqf.query(String.format(ITEMS_SELECTOR, id)).parent().parent().next().find("label"+criteria).parent().prev().find(".ui-radiobutton-box").find(String.format(ITEMS_SELECTOR, id),Element.class);
		 Element e = this.jqf.query(String.format("label"+criteria,id),Element.class);
		// e.jget().add(q);*/	
		String id = this.attr("id");
		return this.jqf.query(String.format(ITEMS_SELECTOR+criteria,id),Element.class);
	}

	@Override
	public String selectedText() {
		String id = this.attr("id");
		int selectNumber = Integer.parseInt(selectedValue())-1;
		String val = this.jqf.query(String.format(ITEMS_SELECTOR, id)).jget(selectNumber).parent().parent().next().find("label").text();
		return val;
	}

	@Override
	public String selectedValue() {
		String id = this.attr("id");
		String val = this.jqf.query(String.format(CHECK_ITEMS_SELECTOR, id)).prev().find("input").val();
		return val;
	}
}