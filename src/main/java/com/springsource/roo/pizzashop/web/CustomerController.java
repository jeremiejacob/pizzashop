package com.springsource.roo.pizzashop.web;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springsource.roo.pizzashop.domain.Customer;

@RequestMapping("/customer/**")
@Controller
public class CustomerController {

    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String create(Model model) {
    	return populateEditForm(model, new Customer(), null);
    }
    
    private String populateEditForm(Model model, Customer customer, BindingResult bindingResult) {
    	model.addAttribute("form", customer);
    	return "customer/edit";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "save")
    private String save(Model model, @Valid @ModelAttribute("form") Customer customer, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return populateEditForm(model, customer, bindingResult);
    	}
    	if(customer.getId() == null) {
    		customer.persist();
    	} else {
    		customer.merge();
    	}
    	return "redirect:/customer/list";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public String list(Model model) {
    	model.addAttribute("customers", Customer.findAllCustomers());
    	return "customer/list";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "show/{id}")
    public String show(Model model, @PathVariable Integer id) {
    	model.addAttribute("customer", Customer.findCustomer(id));
    	return "customer/show";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "update/{id}")
    public String update(Model model, @PathVariable Integer id) {
    	Customer customer = Customer.findCustomer(id);
    	if (customer == null) {
    		return "redirect:/customer/list";
    	}
    	return populateEditForm(model, customer, null);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
    public String delete(@PathVariable Integer id) {
    	Customer customer = Customer.findCustomer(id);
    	if (customer != null) {
    		customer.remove();
    	}
    	return "customer/list";
    }
}
