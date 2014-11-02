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

import com.springsource.roo.pizzashop.domain.Pizza;
import com.springsource.roo.pizzashop.form.PizzaEditForm;
import com.springsource.roo.pizzashop.service.BaseService;
import com.springsource.roo.pizzashop.service.PizzaService;
import com.springsource.roo.pizzashop.service.ToppingService;

@RequestMapping("/pizzas/**")
@Controller
public class PizzaController {
	private static final Logger LOGGER = Logger.getLogger(PizzaController.class);
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private ToppingService toppingService;
	
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return populateEditForm(model, new PizzaEditForm(), null);
	}
	
	private String populateEditForm(Model model, PizzaEditForm form, BindingResult bindingResult) {
		model.addAttribute("form", form);
		model.addAttribute("toppings", toppingService.findAllToppings());
		model.addAttribute("bases", baseService.findAllBases());
		return "pizzas/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	private String save(Model model, @Valid @ModelAttribute("form") PizzaEditForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("hit");
			return populateEditForm(model, form, bindingResult);
		}
		LOGGER.info(form);
		if(form.getId() == null) {
			pizzaService.persist(form.toEntity());
		} else {
			pizzaService.merge(form.toEntity());
		}
		return "redirect:/pizzas/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("pizzas", pizzaService.findAllPizzas());
		model.addAttribute("toppings", toppingService.findAllToppings());
		model.addAttribute("bases", baseService.findAllBases());
		return "pizzas/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Integer id) {
		model.addAttribute("pizza", pizzaService.findPizza(id));
		return "pizzas/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Integer id) {
		Pizza pizza = pizzaService.findPizza(id);
		if (pizza != null) {
			pizzaService.remove(pizza);
		}
		return "redirect:/pizzas/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable Integer id) {
		Pizza pizza = pizzaService.findPizza(id);
		if(pizza == null) {
			return "pizzas/list";
		}
		return populateEditForm(model, PizzaEditForm.fromEntity(pizza), null);
	}
	
//	@InitBinder
//	public void initBinderAll(WebDataBinder binder) {
//		binder.registerCustomEditor(Base.class, new PizzaBasePropertyEditor());
//		binder.registerCustomEditor(Topping.class, new PizzaToppingPropertyEditor());
//	}

}
