package com.kmware.automation.elements.keyboard;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.elements.interfaces.IKeyboard;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = PFKeyboard.SELECTOR
)
public class PFKeyboard extends Element<PFKeyboard> implements IKeyboard<PFKeyboard>{
	public static final String SELECTOR = "[id$=\"keypad-div\"]";
	public static final String SELECTOR_ROW = ".keypad-row";
	public static final String SELECTOR_COLUMN = ".keypad-key";
	
	public PFKeyboard(jQuery j) {
		super(j);
	}

	public PFKeyboard(jQueryFactory jqf, long id, String query) {
		super(jqf, id, query);
	}

	public PFKeyboard(jQueryFactory jqf, long id, WebElement we) {
		super(jqf, id, we);
	}

	public PFKeyboard(jQueryFactory jqf, String reference, jQuery parent) {
		super(jqf, reference, parent);
	}

	public PFKeyboard(jQueryFactory jqf, String reference, String query) {
		super(jqf, reference, query);
	}

	public PFKeyboard(jQueryFactory jqf, String reference, String query,
			jQuery parent) {
		super(jqf, reference, query, parent);
	}

	@Override
	public Element rows(){
		return this.jqf.query(SELECTOR_ROW,Element.class);
	}
	
	@Override
	public Element columns(int row){
		return this.jqf.query(SELECTOR_ROW+":eq("+row+") "+SELECTOR_COLUMN,Element.class);
	}
	
	@Override
	public PFKeyboard selectColumn(int row, int column){
		this.jqf.query(SELECTOR_ROW+":eq("+row+") "+SELECTOR_COLUMN + ":eq("+column+") ",Element.class).click();
		return this;
	}
}
