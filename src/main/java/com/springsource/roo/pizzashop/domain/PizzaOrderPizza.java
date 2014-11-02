package com.springsource.roo.pizzashop.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;


@RooJavaBean
@RooToString
@Entity
@Table(name = "pizza_order_pizza")
public class PizzaOrderPizza implements Serializable{
	
	@Id
    @Column(name = "pizza_order_id")
	private Integer pizzaOrderId;
	
	@Id
    @Column(name = "pizza_id")
	private Integer pizzaId;

}