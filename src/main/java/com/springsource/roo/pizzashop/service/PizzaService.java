package com.springsource.roo.pizzashop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Pizza;

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
