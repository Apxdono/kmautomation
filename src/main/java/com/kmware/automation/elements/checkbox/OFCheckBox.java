package com.kmware.automation.elements.checkbox;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = OFCheckBox.SELECTOR
)
public class OFCheckBox extends Element<OFCheckBox>{
	 public static final String SELECTOR=".form-checkbox:not(.ui-state-disabled)";

	public OFCheckBox(jQuery j) {
		super(j);
	}

	public OFCheckBox(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public OFCheckBox(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public OFCheckBox(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public OFCheckBox(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public OFCheckBox(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

}
