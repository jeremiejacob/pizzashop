package com.springsource.roo.pizzashop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.form.PizzaFilterForm;

@Service
public class PizzaService {
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
		Map<String, Object> parameters = new HashMap<String, Object>();
		String rootQuery = "SELECT c FROM Pizza c ";
		if (form.getName() != null ) {
			rootQuery = rootQuery + "WHERE UPPER(c.name) LIKE UPPER(:name)";
			parameters.put("name", "%" + form.getName() + "%");
		}
		if (form.getPrice() != null ) {
			rootQuery = rootQuery + "c.price=:price";
		}
		
		TypedQuery<Pizza> typedQuery = entityManager.createQuery(rootQuery, Pizza.class);
		for (String key : parameters.keySet()) {
			typedQuery.setParameter(key, parameters.get(key));
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
