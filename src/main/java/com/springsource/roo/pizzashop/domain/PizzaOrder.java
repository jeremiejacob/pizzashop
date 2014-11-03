package com.springsource.roo.pizzashop.domain;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Entity
@Table(name = "pizza_order")
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

    /**
     */
	@Column(name = "total")
    private Float total;

    /**
     */
	@Column(name = "delivery_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
    private Date deliveryDate;

    /**
     */
    @ManyToMany(targetEntity = Pizza.class, fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_order_pizza", joinColumns = @JoinColumn(name = "pizza_order_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzas;
}
