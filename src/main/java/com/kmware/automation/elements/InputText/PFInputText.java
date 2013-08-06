package com.kmware.automation.elements.inputtext;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = PFInputText.SELECTOR
)
public class PFInputText extends Element<PFInputText>{
	 public static final String SELECTOR=".ui-inputfield:visible:not(.ui-state-disabled)";

	public PFInputText(jQuery j) {
		super(j);
	}

	public PFInputText(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFInputText(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFInputText(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFInputText(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFInputText(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}
}