package com.springsource.roo.pizzashop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.domain.Topping;
import com.springsource.roo.pizzashop.form.PizzaFilterForm;
import com.springsource.roo.pizzashop.web.PizzaController;

@Service
public class PizzaService {
	private static final Logger LOGGER = Logger.getLogger(PizzaService.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Pizza> findAllPizzas() {
		return entityManager.createQuery("SELECT o FROM Pizza o", Pizza.class).getResultList();
	}
	
	public Pizza findPizza(Integer id) {
		if (id == null) return null;
		return entityManager.find(Pizza.class, id);
	}
	
	public List<Pizza> findAllPizzasWithCondition(PizzaFilterForm form) {
		Map<String, Object> paramValue = new HashMap<String, Object>();
		List<String> params = new ArrayList<String>();
		List<Topping> tops = new ArrayList<Topping>();
		String rootQuery = "SELECT c FROM Pizza c ";
		if (form.getName() != null ) {
			params.add("UPPER(c.name) LIKE UPPER(:name)");
			paramValue.put("name", "%" + form.getName() + "%");
		}
		if (form.getPrice() != null ) {
			params.add("c.price=:price");
			paramValue.put("price", form.getPrice());
		}
		
		if (form.getBase() != null) {
			params.add("c.base=:base");
			Base nbase = new Base();
			nbase.setId(form.getBase());
			paramValue.put("base", nbase);
		}
//		select distinct pizza.id, pizza.name, pizza.price from pizza left join pizza_topping on pizza.id = pizza_topping.pizza_id where pizza_topping.topping_id in (1,4);
		if (form.getToppings() != null) {
			params.add("c.toppings IN :toppings");
			for (int i=0; i < form.getToppings().size(); i++) {
				Topping ntopping = new Topping();
				ntopping.setId(form.getToppings().get(i));
				tops.add(ntopping);
			}
			paramValue.put("toppings", tops);
		}
		 
		if(params.size() != 0) {
			rootQuery = rootQuery + "WHERE ";
			params.add(null);
			for (int i = 0; i < params.size(); i++) {
				if (params.get(i) != null) {
					if (i == 0) {
						rootQuery += params.get(i);
					} else {
						rootQuery += " AND " + params.get(i);
					}
					
				}
			}
		}
		LOGGER.info("Final Query: " + rootQuery);
		TypedQuery<Pizza> typedQuery = entityManager.createQuery(rootQuery, Pizza.class);
		for (String key : paramValue.keySet()) {
			typedQuery.setParameter(key, paramValue.get(key));
			LOGGER.info("paramValue.get(key); " + key + " : " + paramValue.get(key));
		}
		
		return typedQuery.getResultList();
	}
	
	@Transactional
	public void persist(Pizza pizza) {
		entityManager.persist(pizza);
	}
	
	@Transactional
	public void merge(Pizza pizza) {
		entityManager.merge(pizza);
	}
	
	@Transactional
	public void remove(Pizza pizza) {
		entityManager.remove(pizza);
	}
	
}
