package com.springsource.roo.pizzashop.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "")
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pizza_order_pizza", joinColumns = @JoinColumn(name = "pizza_order_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private Set<Pizza> pizzas = new HashSet<Pizza>();
}
