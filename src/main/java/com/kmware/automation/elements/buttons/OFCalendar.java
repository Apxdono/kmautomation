package com.kmware.automation.elements.buttons;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.enums.Months;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = OFCalendar.SELECTOR
)
public class OFCalendar extends Element<OFCalendar> {
    public static final String SELECTOR=".o_calendar";
    public static final String MONTH_SELECTOR="div[id$=\"%s--month\"]";
    public static final String MONTH_INCREASE_SELECTOR=" div[id$=\"%s::year_increase\"]";
    
    public static final String YEAR_SELECTOR=" div[id$=\"%s--year\"]";
    public static final String YEAR_INCREASE_SELECTOR=" div[id$=\"%s::year_increase\"]";
    public static final String YEAR_DECREASE_SELECTOR=" div[id$=\"%s::year_decrease\"]";
//    div[id$="form:j_idt58--year

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
    	int currentYear = Integer.parseInt(this.jqf.query(String.format(YEAR_SELECTOR, id)).html());
    	int selectedYear = Integer.parseInt(year);
    	if(currentYear<selectedYear){
    		try{
    		this.jqf.query(String.format(YEAR_INCREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setYear(year);
    	}else if(currentYear>selectedYear){
    		try{
    		this.jqf.query(String.format(YEAR_DECREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setYear(year);
    	}
    	return this;
    }
    
    public OFCalendar setMonth(int month){
    	String id = this.attr("id");
    	String currentMonthName = this.jqf.query(String.format(MONTH_SELECTOR, id)).html();
    	int currentMonth = Months.valueOf(currentMonthName).ordinal();
    	if(currentMonth<month){
    		try{
    		this.jqf.query(String.format(MONTH_INCREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setMonth(month);
    	}else if(currentMonth>month){
    		System.out.println(this.jqf.query(String.format(YEAR_INCREASE_SELECTOR, id)));
    		try{
    		System.out.println(this.jqf.query(String.format(YEAR_INCREASE_SELECTOR, id)).click());
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setMonth(month);
    	}
    	return this;
    }
  
}
