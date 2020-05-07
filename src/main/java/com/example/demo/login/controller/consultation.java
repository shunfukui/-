package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.login.domain.model.ConsultationForm;


@Controller

public class consultation {

	
	@GetMapping("/consultation")
    public String getconsultation(ConsultationForm consultationForm,
    		Model model,
    		@ModelAttribute("complete")String complete) {

        return "login/consultation";
    }
	
	@PostMapping("/consultation")
	public String consul(@Validated ConsultationForm consultationForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "項目にエラーがあります");
			return "login/consultation";
		}
		model.addAttribute(attributeName, attributeValue)
		
		redirectAttributes.addFlashAttribute("complete", "投稿完了");
		return "redirect:/login/home";
	
	}

}
