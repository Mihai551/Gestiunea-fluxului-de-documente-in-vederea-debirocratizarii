package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes.AccountType;


@Controller
public class AppController {
	
	@RequestMapping("/") 
	public String showHome(Model theModel) {
		

		AccountType accountType = new AccountType();
		
		theModel.addAttribute("accountType", accountType);
		
		
		
		return "home";
	}
	
	@RequestMapping("/register") 
	public String register(@ModelAttribute("accountType") AccountType theAccountType) {
		
			
		System.out.println(theAccountType.getAccountType());
		
		if ("Individual".equals(theAccountType.getAccountType())) {
			
			
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
	

}



