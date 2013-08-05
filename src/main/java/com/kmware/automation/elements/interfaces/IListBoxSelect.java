package com.kmware.automation.elements.interfaces;

import com.kmware.automation.elements.base.Element;

public interface IListBoxSelect<T> extends IBreadcrumb<T> {
	T selectAt(int index);
    String selectedValue();
    String selectedText();
}
