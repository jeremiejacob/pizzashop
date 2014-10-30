package com.springsource.roo.pizzashop.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsource.roo.pizzashop.domain.Customer;

@Service
public class CustomerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> findAllCustomers() {
		return entityManager.createQuery("SELECT o FROM Customer o", Customer.class).getResultList();
	}
	
	public Customer findCustomer(Integer id) {
		if (id == null) return null;
		return entityManager.find(Customer.class, id);
	}
	
	@Transactional
	public void persist(Customer customer) {
		entityManager.persist(customer);
	}
	
	@Transactional
	public void merge(Customer customer) {
		entityManager.merge(customer);
	}
	
	@Transactional
	public void remove(Customer customer) {
		entityManager.remove(customer);
	}
}
