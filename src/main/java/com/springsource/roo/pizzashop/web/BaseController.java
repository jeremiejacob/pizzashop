package com.springsource.roo.pizzashop.web;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

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
	
	/**
	 * Display create screen
	 */
	@RequestMapping(method = RequestMethod.POST, value = "create")
	public String create(@Valid Base base, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(model, base);
			return "bases/create";
		}
		base.persist();
		return "redirect:/bases/" + encodeUrlSegment(base.getId().toString(), httpServletRequest);
	}
	
	void populateEditForm(Model model, Base base) {
		model.addAttribute("base", base);
	}
	
	String encodeUrlSegment(String pathSegment, HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {}
		return pathSegment;
	}
}
