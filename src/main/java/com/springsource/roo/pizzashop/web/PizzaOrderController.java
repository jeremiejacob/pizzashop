package com.springsource.roo.pizzashop.web;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.domain.PizzaOrder;

@RequestMapping("/pizzaorders/**")
@Controller
public class PizzaOrderController {
	private static final Logger LOGGER = Logger.getLogger(PizzaOrderController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("pizzaorders", PizzaOrder.findAllPizzaOrders());
		return "pizzaorders/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return populateEditForm(model, new PizzaOrder(), null);
	}
	
	private String populateEditForm(Model model, PizzaOrder pizzaorder, BindingResult bindingResult){
		model.addAttribute("form", pizzaorder);
		model.addAttribute("pizzas", Pizza.findAllPizzas());
		return "pizzaorders/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") PizzaOrder pizzaorder, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return populateEditForm(model, pizzaorder, bindingResult);
		}
		if (pizzaorder.getId() == null) {
			LOGGER.info(pizzaorder);
			pizzaorder.persist();
		} else {
			pizzaorder.merge();
		}
		return "redirect:/pizzaorders/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Long id) {
		model.addAttribute("pizzaorder", PizzaOrder.findPizzaOrder(id));
		return "pizzaorders/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Long id) {
		PizzaOrder pizzaorder = PizzaOrder.findPizzaOrder(id);
		if(pizzaorder != null) {
			pizzaorder.remove();
		}
		return "pizzaorders/list";
	}
}
