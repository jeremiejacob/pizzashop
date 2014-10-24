package com.springsource.roo.pizzashop.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springsource.roo.pizzashop.domain.Base;

@RequestMapping("/bases/**")
@Controller
public class BaseController {
	/**
	 * Display list screen
	 */
	@RequestMapping(method = RequestMethod.GET, value="list")
	public String list(Model model) {
		model.addAttribute("bases", Base.findAllBases());
		return "bases/list";
	}
}
