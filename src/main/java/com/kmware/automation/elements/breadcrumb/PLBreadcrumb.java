package com.kmware.automation.elements.breadcrumb;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IBreadcrumb;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PLBreadcrumb.SELECTOR)
public class PLBreadcrumb extends Element<PLBreadcrumb> implements IBreadcrumb<PLBreadcrumb> {
	public static final String SELECTOR = ".ui-breadcrumb";
	public static final String ITEMS_SELECTOR = ".ui-menuitem-link";
	public static final String ITEMS_VALUE_SELECTOR = ".ui-menuitem-text";

	public PLBreadcrumb(jQuery j) {
		super(j);
	} 

	public PLBreadcrumb(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PLBreadcrumb(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PLBreadcrumb(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PLBreadcrumb(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PLBreadcrumb(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

	@Override
	public PLBreadcrumb selectValue(String value) {
		itemsWith(value).click();
		return null;
	}

	public Element itemsWith(String value) {
		String crit = String.format(":has("+ITEMS_VALUE_SELECTOR+":contains(\"%s\"))",value);
		return items(crit);
	}
	
	@Override
	public PLBreadcrumb selectBy(String criteria) {
		items(criteria).click();
		return this;
	}

	@Override
	public Element items() {
		return items("");
	}

	@Override
	public Element items(String criteria) {
		//	$('.ui-menuitem-link:has(.ui-menuitem-text:contains("Sp"))')
		return this.jqf.query(String.format(ITEMS_SELECTOR+"%s",criteria),Element.class);
	}
	
}
