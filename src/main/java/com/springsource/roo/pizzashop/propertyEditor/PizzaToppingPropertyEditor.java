package com.springsource.roo.pizzashop.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.springsource.roo.pizzashop.domain.Topping;

public class PizzaToppingPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		Topping topping = new Topping();
		topping.setId(Integer.parseInt(text));
		setValue(topping);
	}
}
