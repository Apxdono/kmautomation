package com.kmware.automation.elements.buttons;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 17:04
 */
@JQElement(
        extraSelector = PFButton.SELECTOR
)
public class PFButton extends Element {
    public static final String SELECTOR=".ui-button:not(.ui-state-disabled)";

    public PFButton(jQuery j) {
        super(j);
    }

    public PFButton(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public PFButton(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public PFButton(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public PFButton(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public PFButton(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }
}
