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

@RequestMapping("/bases/**")
@Controller
public class BaseController {
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return populateEditForm(model, new Base(), null);
	}
	
	private String populateEditForm(Model model, Base base, BindingResult bindingResult) {
		model.addAttribute("form", base);
		return "bases/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") Base base, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return populateEditForm(model, base, bindingResult);
		}
		if(base.getId() == null) {
			base.persist();
		} else {
			base.merge();
		}
		return "redirect:/bases/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("bases", Base.findAllBases());
		return "bases/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Long id) {
		model.addAttribute("base", Base.findBase(id));
		return "bases/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable Long id) {
		Base base = Base.findBase(id);
		if (base == null) {
			return "bases/list";
		}
		return populateEditForm(model, base, null);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Long id) {
		Base base = Base.findBase(id);
		if (base != null) {
			base.remove();
		}
		return "bases/list";
	}
}

