package com.springsource.roo.pizzashop.form;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.springsource.roo.pizzashop.domain.Customer;
import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.domain.PizzaOrder;

@RooJavaBean
public class PizzaOrderEditForm {
	private static final Logger logger = Logger.getLogger(PizzaOrderEditForm.class);
	
	private Integer id;
	private Integer customer;
	private Float total;
	
	@NotEmpty
	private String deliveryDate;
	private List<Integer> pizzas;
	
	public PizzaOrder toEntity() {
		PizzaOrder pizzaOrder = new PizzaOrder();
		pizzaOrder.setId(id);
		pizzaOrder.setCustomer(new Customer(){{setId(customer);}});
		pizzaOrder.setTotal(total);
		try {
			pizzaOrder.setDeliveryDate(DateUtils.parseDate(deliveryDate, "YYYY-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			pizzaOrder.setDeliveryDate(new Date());
		}
		List<Pizza> piz = new ArrayList<Pizza>();
		for (Integer pizza: pizzas) {
			Pizza npizza = new Pizza();
			npizza.setId(pizza);
			piz.add(npizza);
		}
		pizzaOrder.setPizzas(piz);
		logger.info(pizzaOrder);
		return pizzaOrder;
	}
	
	public static PizzaOrderEditForm fromEntity(PizzaOrder pizzaOrder) {
		List<Integer> piz = new ArrayList<Integer>();
		for (Pizza pizza : pizzaOrder.getPizzas()) {
			piz.add(pizza.getId());
		}
		PizzaOrderEditForm form = new PizzaOrderEditForm();
		form.id = pizzaOrder.getId();
		form.customer = pizzaOrder.getCustomer().getId();
		form.total = pizzaOrder.getTotal();
		form.deliveryDate = pizzaOrder.getDeliveryDate().toString();
		form.pizzas = piz;
		return form;
	}
}
