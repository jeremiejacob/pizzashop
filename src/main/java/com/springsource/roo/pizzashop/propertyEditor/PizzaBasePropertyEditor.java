package com.springsource.roo.pizzashop.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.springsource.roo.pizzashop.domain.Base;

public class PizzaBasePropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		Base base = new Base();
		base.setId(Integer.parseInt(text));
		setValue(base);
	}
}
