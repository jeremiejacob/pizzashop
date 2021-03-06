package com.springsource.roo.pizzashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(max = 50)
	@Column(name = "address")
	private String address;
}
