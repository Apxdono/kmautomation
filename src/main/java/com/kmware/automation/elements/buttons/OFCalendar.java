package com.kmware.automation.elements.buttons;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = OFCalendar.SELECTOR
)
public class OFCalendar extends Element<OFCalendar> {
    public static final String SELECTOR=".o_calendar";
    public static final String MONTH_SELECTOR="div[id$=\"%s--month\"]";
    public static final String YEAR_SELECTOR=" div[id$=\"%s--year\"]";

    public OFCalendar(jQuery j) {
        super(j);
    }

    public OFCalendar(jQueryFactory jqf, long id, String query) {
        super(jqf, id, query);
    }

    public OFCalendar(jQueryFactory jqf, long id, WebElement we) {
        super(jqf, id, we);
    }

    public OFCalendar(jQueryFactory jqf, String reference, jQuery parent) {
        super(jqf, reference, parent);
    }

    public OFCalendar(jQueryFactory jqf, String reference, String query) {
        super(jqf, reference, query);
    }

    public OFCalendar(jQueryFactory jqf, String reference, String query, jQuery parent) {
        super(jqf, reference, query, parent);
    }
    
    public OFCalendar setYear(String year){
    	String id = this.attr("id");
    	System.out.println(this.jqf.query(String.format(YEAR_SELECTOR, id)).html(year));
    	return this;
    }
    
    public OFCalendar setMonth(String month){
    	String id = this.attr("id");
    	System.out.println(this.jqf.query(String.format(MONTH_SELECTOR, id)).html(month));
    	return this;
    }
    
    //увеличение года
    //$('div[id$="form:j_idt58::year_increase"]').click()
}
