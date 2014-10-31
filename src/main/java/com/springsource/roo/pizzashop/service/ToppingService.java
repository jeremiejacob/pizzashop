package com.springsource.roo.pizzashop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Topping;

@Service
public class ToppingService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Topping> findAllToppings() {
		return entityManager.createQuery("SELECT o FROM Topping o", Topping.class).getResultList();
	}
	
	public Topping findTopping(Integer id) {
		if (id == null) return null;
		return entityManager.find(Topping.class, id);
	}
	
	@Transactional
	public void persist(Topping topping) {
		entityManager.persist(topping);
	}
	
	@Transactional
	public void merge(Topping topping) {
		entityManager.merge(topping);
	}
	
	@Transactional
	public void remove(Topping topping) {
		entityManager.remove(topping);
	}
}
