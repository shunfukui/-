package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.login.domain.model.ConsultationForm;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.service.UserConsultationService;



@Controller

public class ConsultationController {
	
private final UserConsultationService userConsultationService;




	
	@Autowired
	public ConsultationController(UserConsultationService userConsultationService) {
		this.userConsultationService = userConsultationService;
	}
	
	@GetMapping
	public String consultations(Model model) {
		List<UserConsultation> list = userConsultationService.getAll();
		
		
		model.addAttribute("consultationsList", list);
		model.addAttribute("title", "home");
		
		return "login/home";
	}

	
	@GetMapping("/consultation")
    public String getconsultation(ConsultationForm consultationForm,
    		Model model,
    		@ModelAttribute("complete")String complete) {

        return "login/consultation";
    }
	
	@PostMapping("/consultation")
	public String consultation(@Validated ConsultationForm consultationForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title", "consultation");
			return "login/consultation";
		}
		
		
		redirectAttributes.addFlashAttribute("complete", "投稿完了");
		return "redirect:/login/home";
	
	}

}
