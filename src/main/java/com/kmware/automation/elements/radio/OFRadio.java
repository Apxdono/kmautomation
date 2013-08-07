package com.kmware.automation.elements.radio;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = OFRadio.SELECTOR
)
public class OFRadio extends Element<OFRadio>{
	 public static final String SELECTOR=".form-radio:visible:not(.ui-state-disabled)";

	public OFRadio(jQuery j) {
		super(j);
	}

	public OFRadio(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public OFRadio(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public OFRadio(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public OFRadio(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public OFRadio(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

}