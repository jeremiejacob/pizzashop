package com.springsource.roo.pizzashop.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "")
public class Customer {

	@NotNull
	@Size(min = 1, max = 30)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 30)
	private String lastName;

	@Size(max = 50)
	private String address;
}
