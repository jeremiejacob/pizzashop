package com.springsource.roo.pizzashop.web;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springsource.roo.pizzashop.domain.PizzaOrder;
import com.springsource.roo.pizzashop.form.PizzaEditForm;
import com.springsource.roo.pizzashop.form.PizzaOrderEditForm;
import com.springsource.roo.pizzashop.service.CustomerService;
import com.springsource.roo.pizzashop.service.PizzaOrderService;
import com.springsource.roo.pizzashop.service.PizzaService;

@RequestMapping("/pizzaorders/**")
@Controller
public class PizzaOrderController {
	private static final Logger LOGGER = Logger.getLogger(PizzaOrderController.class);
	
	@Autowired
	private PizzaOrderService pizzaOrderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("pizzaorders", pizzaOrderService.findAllPizzaOrders());
		return "pizzaorders/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return populateEditForm(model, new PizzaOrderEditForm(), null);
	}
	
	private String populateEditForm(Model model, PizzaOrderEditForm form, BindingResult bindingResult){
		model.addAttribute("form", form);
		model.addAttribute("customers", customerService.findAllCustomers());
		model.addAttribute("pizzas", pizzaService.findAllPizzas());
		return "pizzaorders/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") PizzaOrderEditForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return populateEditForm(model, form, bindingResult);
		}
		if (form.getId() == null) {
			pizzaOrderService.persist(form.toEntity());
		} else {
			pizzaOrderService.merge(form.toEntity());
		}
		return "redirect:/pizzaorders/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Integer id) {
		model.addAttribute("pizzaorder", pizzaOrderService.findPizzaOrder(id));
		return "pizzaorders/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Integer id) {
		PizzaOrder pizzaorder = pizzaOrderService.findPizzaOrder(id);
		if(pizzaorder != null) {
			pizzaOrderService.remove(pizzaorder);
		}
		return "redirect:/pizzaorders/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable Integer id) {
		PizzaOrder pizzaOrder = pizzaOrderService.findPizzaOrder(id);
		if(pizzaOrder == null) {
			return "pizzaorders/list";
		}
		return populateEditForm(model, PizzaOrderEditForm.fromEntity(pizzaOrder), null);
	}
}
