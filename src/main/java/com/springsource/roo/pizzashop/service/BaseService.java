package com.springsource.roo.pizzashop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.form.BaseFilterForm;

@Service
public class BaseService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Base> findAllBases() {
		return entityManager.createQuery("SELECT o FROM Base o", Base.class).getResultList();
	}
	
	public Base findBase(Integer id) {
		return entityManager.createQuery("SELECT c FROM Base c WHERE c.id = :id", Base.class).setParameter("id", id).getSingleResult();
	}
	
	public List<Base> findAllBasesWithCondition(BaseFilterForm form) {
		String query = "SELECT c FROM Base c WHERE UPPER(c.name) LIKE UPPER(:name)";
		String value = form.getName() == null ? "" : form.getName();
		return entityManager.createQuery(query, Base.class).setParameter("name", "%" + value + "%").getResultList();
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
