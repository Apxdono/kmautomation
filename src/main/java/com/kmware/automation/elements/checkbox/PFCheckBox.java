package com.kmware.automation.elements.checkbox;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = PFCheckBox.SELECTOR
)
public class PFCheckBox extends Element<PFCheckBox>{
	 public static final String SELECTOR=".ui-chkbox-box:visible:not(.ui-state-disabled)";

	public PFCheckBox(jQuery j) {
		super(j);
	}

	public PFCheckBox(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFCheckBox(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFCheckBox(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFCheckBox(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFCheckBox(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

}
