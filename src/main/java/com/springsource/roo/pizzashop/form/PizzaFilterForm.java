package com.springsource.roo.pizzashop.form;

import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class PizzaFilterForm {
	private String name;
	private Float price;
	private Integer base;
	private List<Integer>toppings;
}
