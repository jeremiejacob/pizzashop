package com.springsource.roo.pizzashop.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.domain.Topping;

@RooJavaBean
public class PizzaEditForm {
	private static final Logger logger = Logger.getLogger(PizzaEditForm.class);
	
	private Integer id;
	
	@NotNull
	@Size(min = 2)
	private String name;
	
	private Float price;

	private List<Integer> toppings;
	
	private Integer base;
	
	public Pizza toEntity() {
		Pizza pizza = new Pizza();
		pizza.setId(id);
		pizza.setName(name);
		pizza.setPrice(price);
		List<Topping> tops = new ArrayList<Topping>();
		for (Integer topping : toppings) {
			Topping ntopping = new Topping();
			ntopping.setId(topping);
			tops.add(ntopping);
		}
		pizza.setToppings(tops);
		pizza.setBase(new Base(){{setId(base);}});
		logger.info(pizza);
		return pizza;
	}
	
	public static PizzaEditForm fromEntity(Pizza pizza) {
        List<Integer> tops = new ArrayList<Integer>();
        for (Topping topping : pizza.getToppings()) {
        	tops.add(topping.getId());
        }
        PizzaEditForm form = new PizzaEditForm();
        form.id = pizza.getId();
        form.name = pizza.getName();
        form.price = pizza.getPrice();
        form.toppings = tops;
        form.base = pizza.getBase().getId();
        return form;
    }
}
