package com.kmware.automation.elements.interfaces;

import com.kmware.automation.elements.base.Element;

public interface IBreadcrumb<T> {
    T selectValue(String value);
    T selectBy(String criteria);
    Element items();
    Element items(String criteria);
    Element itemsWith(String value);
}
