package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {
	
	@GetMapping("/") 
	public String showHome() {
		
		return "home";
	}

}



