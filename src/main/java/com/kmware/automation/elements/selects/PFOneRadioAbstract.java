package com.kmware.automation.elements.selects;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.interfaces.IListBoxSelect;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

public abstract class PFOneRadioAbstract<T extends PFOneRadioAbstract> extends Element<T> implements IListBoxSelect<T>{

	public PFOneRadioAbstract(jQuery j) {
		super(j);
	} 

	public PFOneRadioAbstract(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFOneRadioAbstract(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFOneRadioAbstract(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFOneRadioAbstract(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFOneRadioAbstract(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}
	@Override
	public T selectAt(int index) {
		String id = this.attr("id");
		this.jqf.query(String.format(getItemSelector(), id)).jget(index).as(Element.class).nclick();
		return (T)this;
	}
	
	@Override
	public T selectValue(String value) {
		String id = this.attr("id");
		this.jqf.query(String.format(getItemSelector(), id)).parent().parent().next().find("label:contains("+value+")").parent().prev().find(".ui-radiobutton-box").click();
        return (T)this;
	}

	@Override
	public T selectBy(String criteria) {
		items(criteria).click();
		return (T)this;
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
		String id = this.attr("id");
		return this.jqf.query(String.format(getItemSelector()+criteria,id),Element.class);
	}

	@Override
	public String selectedText() {
		String id = this.attr("id");
		int selectNumber = Integer.parseInt(selectedValue())-1;
		String val = this.jqf.query(String.format(getItemSelector(), id)).jget(selectNumber).parent().parent().next().find("label").text();
		return val;
	}

	@Override
	public String selectedValue() {
		String id = this.attr("id");
		String val = this.jqf.query(String.format(getCheckItemSelector(), id)).prev().find("input").val();
		return val;
	}

	public abstract String getItemSelector();
	public abstract String getCheckItemSelector();
	
}
