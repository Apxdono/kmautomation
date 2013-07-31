package com.kmware.automation.elements.interfaces;

import com.kmware.automation.elements.base.Element;

public interface IListBoxSelect<T> {
	T selectAt(int index);
    T selectValue(String value);
    T selectBy(String criteria);
    Element items();
    Element itemsWith(String value);
    Element items(String criteria);
    String selectedText();
    String selectedValue();
}
