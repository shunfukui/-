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
	 @Autowired
	   UserConsultationService userConsultationService;
	
	 
	 
	 
	 
	 @GetMapping("/answer")
	    public String getanswer(Model model,AnswerForm answerForm,
	    		UserConsultation userConsultation,
	    		@ModelAttribute("userName") String form1,
	    		@ModelAttribute("title") String form2,
	    		@ModelAttribute("content") String form3) {
		 
		//コンテンツ部分にユーザー一覧を表示するための文字列を登録
	     model.addAttribute("contents", "login/answer :: answer_contents");
	     

	     UserConsultation list1 = new UserConsultation();
		 List<UserAnswer> list = userAnswerService.getOne(userConsultation);
		
		 
		 list1.setUserName(form1); //ユーザー名
         list1.setTitle(form2); //タイトル
         list1.setContent(form3); //内容
         
         model.addAttribute("ConsultationList", list1);
		 model.addAttribute("AnswerList", list);
			
	    	 
		 return "login/answer";
	    }
	 
	 @PostMapping("/answer")
	    public String postanswer(@Validated AnswerForm answerForm,
				BindingResult result,
				Model model,
				RedirectAttributes redirectAttributes)
	    		{
		 UserAnswer form = new UserAnswer();
		 
		 System.out.println(answerForm);
		 System.out.println(form);
		 
		 form.setUserName(answerForm.getUserName()); // 
		 form.setTitle(answerForm.getTitle()); //
		 form.setAnswerContent(answerForm.getContent()); //
	    	

			if(result.hasErrors()) {
				model.addAttribute("title", "answer");
				return "login/answer";
			}
			boolean userAnswer  = userAnswerService.insert(form);
			 model.addAttribute("registration", "回答登録");
			 
			 model.addAttribute("username", answerForm.getUserName());
			 model.addAttribute("title", answerForm.getTitle());
			 model.addAttribute("content", answerForm.getContent());
			 
			 
			
			redirectAttributes.addFlashAttribute("complete", "投稿完了");
			return "redirect:/answer";
	    }


}
