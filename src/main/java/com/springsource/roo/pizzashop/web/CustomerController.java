package com.springsource.roo.pizzashop.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springsource.roo.pizzashop.domain.Customer;

@RequestMapping("/customer/**")
@Controller
public class CustomerController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "customer/index";
    }
    
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
    	if(customer.getId() == null ) {
    		customer.persist();
    	} 
    	return "redirect:/customer/list";
    }
}
