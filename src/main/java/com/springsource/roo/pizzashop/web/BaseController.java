package com.springsource.roo.pizzashop.web;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.springsource.roo.pizzashop.domain.Base;

@RequestMapping("/bases")
@Controller
public class BaseController {
	/**
	 * 
	 * Display create screen
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Base base, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, base);
            return "bases/create";
        }
        uiModel.asMap().clear();
        base.persist();
        return "redirect:/bases/" + encodeUrlPathSegment(base.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Base());
        return "bases/create";
    }
    /**
     * 
     * Display show screen
     */
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("base", Base.findBase(id));
        uiModel.addAttribute("itemId", id);
        return "bases/show";
    }
    /**
     * 
     * Display list screen
     */
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bases", Base.findBaseEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Base.countBases() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bases", Base.findAllBases(sortFieldName, sortOrder));
        }
        return "bases/list";
    }
    /**
     * 
     * Display update screen
     */
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Base base, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, base);
            return "bases/update";
        }
        uiModel.asMap().clear();
        base.merge();
        return "redirect:/bases/" + encodeUrlPathSegment(base.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Base.findBase(id));
        return "bases/update";
    }
    /**
     * 
     * Delete item
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Base base = Base.findBase(id);
        base.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/bases";
    }
    
    void populateEditForm(Model uiModel, Base base) {
        uiModel.addAttribute("base", base);
    }
    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
