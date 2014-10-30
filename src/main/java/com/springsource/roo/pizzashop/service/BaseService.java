package com.springsource.roo.pizzashop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Base;

@Service
public class BaseService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Base> findAllBases() {
		return entityManager.createQuery("SELECT o FROM Base o", Base.class).getResultList();
	}
	
	public Base findBase(Integer id) {
		if (id == null) return null;
		return entityManager.find(Base.class, id);
	}
	
	@Transactional
	public void persist(Base base) {
		entityManager.persist(base);
	}
	
	@Transactional
	public void merge(Base base) {
		entityManager.merge(base);
	}
	
	@Transactional
	public void remove(Base base) {
		entityManager.remove(base);
	}	
}
