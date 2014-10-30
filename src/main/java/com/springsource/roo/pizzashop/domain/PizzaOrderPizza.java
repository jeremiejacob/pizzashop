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
public class PizzaOrderPizza {
	
	@Id
    @Column(name = "pizza_order_id")
	private Integer pizzaOrderId;
	
	@Id
    @Column(name = "pizza_id")
	private Integer pizzaId;

}