package com.example.demo.login.controller;

import java.security.Principal;

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
import com.example.demo.login.domain.service.UserService;



@Controller

public class ConsultationController {


UserConsultationService userConsultationService;
UserConsultation userConsultation;
@Autowired
UserService userService;


	@Autowired
	public ConsultationController(UserConsultationService userConsultationService) {
		this.userConsultationService = userConsultationService;
	}
	
	@GetMapping("/consultation")
    public String getconsultation(ConsultationForm consultationForm,
    		Model model,
    		Principal principal,
    		@ModelAttribute("complete")String complete,
    		@ModelAttribute("userName") String userName) {
		
		UserConsultation userConsultationName = new UserConsultation();
		 principal.getName();
		 userConsultationName.setUserName(userName); // 
		 
		 
		 //ユーザー一覧の生成
	     String myName = userService.selectName( principal.getName());
	        
	     //Modelにユーザーリストを登録
	     model.addAttribute("myPage", myName);
	  
		 UserConsultation nameList = new UserConsultation();
		
		 nameList.setUserName(userName); //ユーザー名
        
		 model.addAttribute("nameList", nameList);
		 
		 ConsultationForm nameList2 = new ConsultationForm();
	
		 nameList2.setUserName(myName); //ユーザー名
		 
		 
		 model.addAttribute("consultationForm", nameList2);
		 

			return "login/consultation";
	}
		
		
		
	


	@PostMapping("/consultation")
	public String consultation(@Validated ConsultationForm consultationForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes,
			@ModelAttribute("userName") String userName,
			@ModelAttribute("userConsultation") UserConsultation form
			) {

		if(result.hasErrors()) {
			//引数を渡している
			//@が付いている引数はフラッシュアトリビュートを使う
			//それ以外はアドアトリビュート
			redirectAttributes.addFlashAttribute("userName", consultationForm.getUserName());
			model.addAttribute("title", "consultation");
			return "redirect:consultation";
		}
		
		
		UserConsultation userConsultationName = new UserConsultation();
		 
		userConsultationName.setUserName(userName); // 
		
		ConsultationForm nameList2 = new ConsultationForm();
		
		nameList2.setUserName(userName); //ユーザー名

		model.addAttribute("consultationForm", nameList2);
		
		
		
		//データ件数を取得
        boolean count = userConsultationService.count(form);
        
        if(count == false) {
        model.addAttribute("false", false);
        model.addAttribute("allready", "その相談内容はすでにあります。");
    
        return "login/consultation";
        }
        
    
		boolean userConsultation  = userConsultationService.insert(form);
		 model.addAttribute("registration", "相談内容登録");
		 
		redirectAttributes.addFlashAttribute("userName", consultationForm.getUserName());
		redirectAttributes.addFlashAttribute("complete", "投稿完了");
		return "redirect:/home";
	}
	
	
		


}
