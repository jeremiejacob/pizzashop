package com.springsource.roo.pizzashop.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "")
@IdClass(PizzaToppingId.class)
public class PizzaTopping {
	
	@Id
    @Column(name = "pizza_id")
	private Integer pizzaId;
	
	@Id
    @Column(name = "topping_id")
	private Integer toppingId;

}
