package com.Languages.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Languages.models.Languages;
import com.Languages.services.LanguagesService;

@Controller
public class LanguagesController {
	private final LanguagesService languageService;
	
	public LanguagesController(LanguagesService languageService) {
		this.languageService = languageService;
	}

	@RequestMapping("/languages")
	public String index(@ModelAttribute("addNew") Languages languages, Model model) {
		List<Languages> languages1 = languageService.allLanguages();
		model.addAttribute("languages", languages1);
		return "index.jsp";
	}

	@RequestMapping(value="/languages", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("addNew") Languages languages, BindingResult result, Model model) {
        if (result.hasErrors()) {
    		List<Languages> languages1 = languageService.allLanguages();
    		model.addAttribute("languages", languages1);
            return "index.jsp";
        }
        else {
            languageService.addLanguage(languages);
            return "redirect:/languages";
        }
	}

	@RequestMapping(value="/languages/{id}", method=RequestMethod.PUT)
	public String modify(@Valid @ModelAttribute("language") Languages language, BindingResult result) {
        if (result.hasErrors()) {
            return "edit.jsp";
        }
        else {
            languageService.addLanguage(language);
            return "redirect:/languages";
        }
	}
	
	@RequestMapping("/languages/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Languages language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "show.jsp";
	}
	
	@RequestMapping("/languages/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Languages language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "edit.jsp";
	}
	
	@RequestMapping("/languages/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		languageService.deleteLanguage(id);
		return "redirect:/languages";
	}
}
