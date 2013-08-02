package com.kmware.automation.elements.buttons;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

public class PFCommandLink extends Element {
    public static final String SELECTOR=".ui-commandlink:not(.ui-state-disabled)";

    public PFCommandLink(jQuery j) {
        super(j);
    }

    public PFCommandLink(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public PFCommandLink(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public PFCommandLink(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public PFCommandLink(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public PFCommandLink(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }
}
