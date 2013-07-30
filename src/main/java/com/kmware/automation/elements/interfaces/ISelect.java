package com.kmware.automation.elements.interfaces;

import com.kmware.automation.elements.base.Element;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 15:31
 */
public interface ISelect<T> {
    T expand();
    T selectAt(int index);
    T selectValue(String value);
    T selectBy(String criteria);
    Element items();
    Element itemsWith(String value);
    Element items(String criteria);
    String selectedText();
    String selectedValue();
}
