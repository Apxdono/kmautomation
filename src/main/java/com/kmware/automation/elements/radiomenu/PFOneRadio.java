package com.kmware.automation.elements.radiomenu;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(extraSelector = PFOneRadio.SELECTOR)
public class PFOneRadio extends PFOneRadioAbstract<PFOneRadio> {
	public static final String SELECTOR = ".ui-selectoneradio";
	public static final String ITEMS_SELECTOR = "table[id$=\"%s\"] .ui-radiobutton .ui-radiobutton-box";
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
	public String getItemSelector() {
		return ITEMS_SELECTOR;
	}

	@Override
	public String getCheckItemSelector() {
		return CHECK_ITEMS_SELECTOR;
	}	
}