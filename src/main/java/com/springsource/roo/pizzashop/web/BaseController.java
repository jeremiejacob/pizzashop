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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.form.BaseFilterForm;
import com.springsource.roo.pizzashop.service.BaseService;

@RequestMapping("/bases/**")
@Controller
public class BaseController {
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);
	
	@Autowired
	private BaseService baseService;
	
	@RequestMapping(method = RequestMethod.GET, value = "create") String create(Model model) {
		return populateEditForm(model, new Base(), null);
	}
	
	private String populateEditForm(Model model, Base base, BindingResult bindingResult) {
		model.addAttribute("form", base);
		return "bases/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") Base base, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return populateEditForm(model, base, bindingResult);
		}
		if(base.getId() == null) {
			baseService.persist(base);
			redirectAttributes.addFlashAttribute("message", "common_added_successfully");
		} else {
			baseService.merge(base);
			redirectAttributes.addFlashAttribute("message", "common_updated_successfully");
		}
		return "redirect:/bases/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model, @ModelAttribute("form") BaseFilterForm form) {
		model.addAttribute("bases", baseService.findAllBasesWithCondition(form));
		return "bases/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable Integer id) {
		model.addAttribute("base", baseService.findBase(id));
		return "bases/show";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable Integer id) { 
		Base base = baseService.findBase(id);
		if (base == null) {
			return "bases/list";
		}
		return populateEditForm(model, base, null);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Base base = baseService.findBase(id);
		if (base != null) {
			baseService.remove(base);
			redirectAttributes.addFlashAttribute("messsage", "common_deleted_successfully");
		}
		return "redirect:/bases/list";
	}
}

