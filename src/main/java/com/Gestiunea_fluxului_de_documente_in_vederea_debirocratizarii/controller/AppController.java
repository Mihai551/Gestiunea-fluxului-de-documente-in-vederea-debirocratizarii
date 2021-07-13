package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes.AccountType;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes.Individual;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;


@Controller
public class AppController {
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	//=========================================================================================================================================================
	
	@RequestMapping("/") 
	public String showHome(Model theModel) {
		

		AccountType accountType = new AccountType();
		
		theModel.addAttribute("accountType", accountType);
		
		
		
		return "home";
	}
	
	//=========================================================================================================================================================
	
	@RequestMapping("/register") 
	public String register(Model theModel,
			@ModelAttribute("accountType") AccountType theAccountType) {
		
			
		
		if ("Individual".equals(theAccountType.getAccountType())) {
			
			ClassPathXmlApplicationContext context = 
					new ClassPathXmlApplicationContext("applicationContext.xml");
			
			User theUser = context.getBean("individual", User.class);
			
			theModel.addAttribute("user", theUser);
			
			context.close();
			
			return "Individual-register";
		}
			
			
			
				if ("Legal entity".equals(theAccountType.getAccountType())) {
				
				
				return "Legal-Entity-register";
				}
			
			
			
			
					if ("Employee".equals(theAccountType.getAccountType())) {
				
				
				return "Employee-register";
					}
			
					else return "home";
			
	
	}
	
	//=========================================================================================================================================================
	
	@RequestMapping("/processForm_Individual") 
		public String processForm(
				@Valid @ModelAttribute("user") Individual theUser,
				BindingResult theBindingResult) {
			
			if (theBindingResult.hasErrors()) {
				return "Individual-register";
			}
			else {
				return "user-confirmation";
			}
	}
		
	
	
	

}



