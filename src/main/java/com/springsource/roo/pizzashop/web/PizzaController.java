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

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.domain.Topping;

@RequestMapping("/pizzas/**")
@Controller
public class PizzaController {
	private static final Logger LOGGER = Logger.getLogger(PizzaController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return populateEditForm(model, new Pizza(), null);
	}
	
	private String populateEditForm(Model model, Pizza pizza, BindingResult bindingResult) {
		model.addAttribute("form", pizza);
		model.addAttribute("toppings", Topping.findAllToppings());
		model.addAttribute("bases", Base.findAllBases());
		return "pizzas/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	private String save(Model model, @Valid @ModelAttribute("form") Pizza pizza, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("hit");
			return populateEditForm(model, pizza, bindingResult);
		}
		if(pizza.getId() == null) {
			pizza.persist();
		} else {
			pizza.merge();
		}
		return "redirect:/pizzas/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("pizzas", Pizza.findAllPizzas());
		model.addAttribute("toppings", Topping.findAllToppings());
		model.addAttribute("bases", Base.findAllBases());
		return "pizzas/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Integer id) {
		model.addAttribute("pizza", Pizza.findPizza(id));
		return "pizzas/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Integer id) {
		Pizza pizza = Pizza.findPizza(id);
		if (pizza != null) {
			pizza.remove();
		}
		return "pizzas/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable Integer id) {
		Pizza pizza = Pizza.findPizza(id);
		if(pizza == null) {
			return "pizzas/list";
		}
		return populateEditForm(model, pizza, null);
	}
}
