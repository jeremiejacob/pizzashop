package com.springsource.roo.pizzashop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.PizzaOrder;

@Service
public class PizzaOrderService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<PizzaOrder> findAllPizzaOrders() {
		return entityManager.createQuery("SELECT o FROM PizzaOrder o", PizzaOrder.class).getResultList();
	}
	
	public PizzaOrder findPizzaOrder(Integer id) {
		if (id == null) return null;
		return entityManager.find(PizzaOrder.class, id);
	}
	
	@Transactional
	public void persist(PizzaOrder pizzaOrder) {
		entityManager.persist(pizzaOrder);
	}
	
	@Transactional
	public void merge(PizzaOrder pizzaOrder) {
		entityManager.merge(pizzaOrder);
	}
	
	@Transactional
	public void remove(PizzaOrder pizzaOrder) {
		entityManager.remove(pizzaOrder);
	}	

}
