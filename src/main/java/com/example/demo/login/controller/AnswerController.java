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

import com.example.demo.login.domain.model.AnswerForm;
import com.example.demo.login.domain.model.ConsultationForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserAnswer;
import com.example.demo.login.domain.model.UserConsultation;
import com.example.demo.login.domain.service.UserAnswerService;
import com.example.demo.login.domain.service.UserConsultationService;

@Controller
public class AnswerController {
	
	 @Autowired
	   UserAnswerService userAnswerService;
	
	 @GetMapping("/answer")
	    public String getanswer(Model model,AnswerForm answerForm) {
		 
		//コンテンツ部分にユーザー一覧を表示するための文字列を登録
	        model.addAttribute("contents", "login/answer :: answer_contents");
	        
	
		 List<UserAnswer> list = userAnswerService.getAll();
		 
		 
		 model.addAttribute("AnswerList", list);
			
	    	 
		 return "login/answer";
	    }
	 
	 @PostMapping("/answer")
	    public String postanswer(@Validated AnswerForm answerForm,
				BindingResult result,
				Model model,
				RedirectAttributes redirectAttributes,
				@ModelAttribute("userAnswer") UserAnswer form) {
	    	

			if(result.hasErrors()) {
				model.addAttribute("title", "answer");
				return "login/answer";
			}
			boolean userAnswer  = userAnswerService.insert(form);
			 model.addAttribute("registration", "回答登録");
			 
			 
			
			redirectAttributes.addFlashAttribute("complete", "投稿完了");
			return "redirect:/answer";
	    }


}
