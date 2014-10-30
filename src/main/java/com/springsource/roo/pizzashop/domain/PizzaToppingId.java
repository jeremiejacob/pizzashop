package com.springsource.roo.pizzashop.domain;

import java.io.Serializable;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@RooEquals
public class PizzaToppingId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pizzaId;

    private Integer toppingId;

}
