package com.springsource.roo.pizzashop.domain;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Entity
@Table(name = "pizza")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

    /**
     */
    @NotNull
    @Size(min = 2)
    @Column(name = "name")
    private String name;

    /**
     */
    @Column(name = "price")
    @NotNull
    private Float price;

    /**
     */
    @ManyToMany(targetEntity = Topping.class, fetch = FetchType.LAZY)
    @JoinTable(name = "pizza_topping", joinColumns = @JoinColumn(name = "pizza_id") , inverseJoinColumns = @JoinColumn(name = "topping_id"))
    private List<Topping> toppings;

    /**
     */
    @ManyToOne()
    @JoinColumn(name = "base_id")
    private Base base;
    
}
