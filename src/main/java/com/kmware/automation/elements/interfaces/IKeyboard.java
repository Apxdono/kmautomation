package com.kmware.automation.elements.interfaces;

import com.kmware.automation.elements.base.Element;

public interface IKeyboard<T> {
	
	public Element rows();
	public Element columns(int row);
	public T selectColumn(int row, int column);
}
