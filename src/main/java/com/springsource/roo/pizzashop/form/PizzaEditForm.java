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
	private List<Integer> toppingIds;
	private Integer baseId;
	
	public Pizza toEntity() {
		Pizza pizza = new Pizza();
		pizza.setId(id);
		pizza.setName(name);
		pizza.setPrice(price);
		List<Topping> toppings = new ArrayList<Topping>();
		for (Integer toppingId : toppingIds) {
			Topping ntopping = new Topping();
			ntopping.setId(toppingId);
			toppings.add(ntopping);
		}
		pizza.setToppings(toppings);
		pizza.setBase(new Base(){{setId(baseId);}});
		return pizza;
	}
	
	public static PizzaEditForm fromEntity(Pizza pizza) {
        List<Integer> toppingIds = new ArrayList<Integer>();
        for (Topping topping : pizza.getToppings()) {
        	toppingIds.add(topping.getId());
        }
        PizzaEditForm form = new PizzaEditForm();
        form.id = pizza.getId();
        form.name = pizza.getName();
        form.price = pizza.getPrice();
        form.toppingIds = toppingIds;
        form.baseId = pizza.getBase().getId();
        return form;
    }
}
