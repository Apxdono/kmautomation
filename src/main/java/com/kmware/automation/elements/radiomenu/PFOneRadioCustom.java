package com.kmware.automation.elements.radiomenu;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PFOneRadioCustom.SELECTOR)
public class PFOneRadioCustom extends PFOneRadioAbstract<PFOneRadioCustom> {
	public static final String SELECTOR = ".ui-outputpanel";
	public static final String ITEMS_SELECTOR = "div[id$=\"%s\"] .ui-radiobutton .ui-radiobutton-box";
	public static final String CHECK_ITEMS_SELECTOR = "div[id$=\"%s\"] .ui-radiobutton .ui-state-active";
	
	public PFOneRadioCustom(jQuery j) {
		super(j);
	} 

	public PFOneRadioCustom(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFOneRadioCustom(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFOneRadioCustom(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFOneRadioCustom(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFOneRadioCustom(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

	@Override
	public String getItemSelector() {
		return ITEMS_SELECTOR;
	}

	@Override
	public String getCheckItemSelector() {
		return CHECK_ITEMS_SELECTOR;
	}	
	
}
