package com.kmware.automation.elements.calendar;

import org.openqa.selenium.WebElement;

import com.kmware.automation.elements.base.Element;
import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.enums.MonthsEU;
import com.kmware.automation.enums.MonthsRU;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

@JQElement(
        extraSelector = OFCalendar.SELECTOR
)
public class OFCalendar extends Element<OFCalendar> {
    public static final String SELECTOR=".o_calendar";
    public static final String MONTH_SELECTOR="div[id$=\"%s--month\"]";
    public static final String MONTH_INCREASE_SELECTOR=" div[id$=\"%s::month_increase\"]";
    public static final String MONTH_DECREASE_SELECTOR=" div[id$=\"%s::month_decrease\"]";
    
    public static final String YEAR_SELECTOR=" div[id$=\"%s--year\"]";
    public static final String YEAR_INCREASE_SELECTOR=" div[id$=\"%s::year_increase\"]";
    public static final String YEAR_DECREASE_SELECTOR=" div[id$=\"%s::year_decrease\"]";
   
    public static final String DAY_SELECTOR="tbody[id$=\"%s::body\"] .o_calendar_day:not(.o_calendar_inactive_month_day)";
    
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
    
    public OFCalendar setYear(int year){
    	String id = this.attr("id");
    	int currentYear = Integer.parseInt(this.jqf.query(String.format(YEAR_SELECTOR, id)).html());
    	if(currentYear<year){
    		try{
    		this.jqf.query(String.format(YEAR_INCREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setYear(year);
    	}else if(currentYear>year){
    		try{
    		this.jqf.query(String.format(YEAR_DECREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setYear(year);
    	}
    	return this;
    }
    
    public OFCalendar setMonth(int month, String language){
    	String id = this.attr("id");
    	String currentMonthName = this.jqf.query(String.format(MONTH_SELECTOR, id)).html();
    	int currentMonth = language.toUpperCase().equals("RU") ? MonthsRU.valueOf(currentMonthName).ordinal() : MonthsEU.valueOf(currentMonthName).ordinal();
    	if(currentMonth<month){
    		try{
    		this.jqf.query(String.format(MONTH_INCREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setMonth(month, language);
    	}else if(currentMonth>month){
    		try{
    		this.jqf.query(String.format(MONTH_DECREASE_SELECTOR, id)).click();
    		}catch (Exception e) {
    			System.err.println("Error in OFCalendar");
			}
    		setMonth(month, language);
    	}
    	return this;
    }
    
    public OFCalendar setDay(final int day){
    	String id = this.attr("id");
    	this.jqf.query(String.format(DAY_SELECTOR,id),Element.class).each(new Eacher() {
			
			@Override
			public void invoke(Integer index, WebElement ele) {
				if(day == Integer.parseInt(ele.getText())){
					ele.click();
				}
			}
		});
    	
    	
    	return this;
    }
    
    public OFCalendar setData(int year, int month, String language, int day){
    	setYear(year);
    	setMonth(month, language);
    	setDay(day);
    	return this;
    }
     
  //.setSelectedDate(new Date("21 May 2013"))
}
