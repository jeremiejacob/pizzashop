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

import com.springsource.roo.pizzashop.domain.Topping;
import com.springsource.roo.pizzashop.service.ToppingService;

@RequestMapping("/toppings/**")
@Controller
public class ToppingController {
private static final Logger LOGGER = Logger.getLogger(BaseController.class);
	
	@Autowired
	private ToppingService toppingService;
	
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return populateEditForm(model, new Topping(), null);
	}
	
	private String populateEditForm(Model model, Topping topping, BindingResult bindingResult) {
		model.addAttribute("form", topping);
		return "toppings/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") Topping topping, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return populateEditForm(model, topping, bindingResult);
		}
		if(topping.getId() == null) {
			toppingService.persist(topping);
		} else {
			toppingService.merge(topping);
		}
		return "redirect:/toppings/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("toppings", toppingService.findAllToppings());
		return "toppings/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Integer id) {
		model.addAttribute("topping", toppingService.findTopping(id));
		return "toppings/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable Integer id) {
		Topping topping = toppingService.findTopping(id);
		if (topping == null) {
			return "toppings/list";
		}
		return populateEditForm(model, topping, null);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Integer id) {
		Topping topping = toppingService.findTopping(id);
		if (topping != null) {
			toppingService.remove(topping);
		}
		return "redirect:/toppings/list";
	}
}
