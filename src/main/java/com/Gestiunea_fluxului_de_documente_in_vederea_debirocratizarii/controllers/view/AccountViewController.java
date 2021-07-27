package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.view;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;

@Controller
public class AccountViewController {

	// @InitBinder
	// public void initBinder(WebDataBinder dataBinder) {

	// StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

	// .registerCustomEditor(String.class, stringTrimmerEditor);
	// }

	// =========================================================================================================================================================

	@RequestMapping("/")
	public String showHome(Model theModel) {

		AccountType accountType = new AccountType();

		theModel.addAttribute("accountType", accountType);

		return "home";
	}

	// =========================================================================================================================================================

	@RequestMapping("/register")
	public String register(Model theModel, @ModelAttribute("accountType") AccountType theAccountType) {

		if ("Individual".equals(theAccountType.getAccountType())) {

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			User theUser = context.getBean("individual", User.class);

			theModel.addAttribute("user", theUser);

			context.close();

			return "Individual-register";
		}

		if ("Legal entity".equals(theAccountType.getAccountType())) {

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			User theUser = context.getBean("legalEntity", User.class);

			theModel.addAttribute("user", theUser);

			context.close();

			return "Legal-Entity-register";
		}

		if ("Employee".equals(theAccountType.getAccountType())) {

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			User theUser = context.getBean("employee", User.class);

			theModel.addAttribute("user", theUser);

			context.close();

			return "Employee-register";
		}

		else
			return "home";

	}

	// =========================================================================================================================================================

	@RequestMapping("/individual-confirmation")
	public String processForm(@Valid @ModelAttribute("user") Individual theUser, BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "Individual-register";
		} else {
			return "Individual-confirmation";
		}
	}

	// =========================================================================================================================================================

	@RequestMapping("/legal-entity-confirmation")
	public String processForm_LegalEntity(@Valid @ModelAttribute("user") LegalEntity theUser,
			BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "Legal-Entity-register";
		} else {
			return "Legal-Entity-confirmation";
		}
	}

	// =========================================================================================================================================================

	@RequestMapping("/employee-confirmation")
	public String processForm_Employee(@Valid @ModelAttribute("user") Employee theUser,
			BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "Employee-register";
		} else {
			return "Employee-confirmation";
		}
	}

	@RequestMapping("/login")
	public String LoginView(Model theModel) {

		SimpleUser theUser = new SimpleUser();
		theModel.addAttribute("SimpleUser", theUser);

		return "login";
	}

	@RequestMapping("/user-menu")
	public String user_menu(Model theModel, @ModelAttribute("SimpleUser") SimpleUser theUser) {

		theModel.addAttribute("SimpleUser", theUser);

		return "user-menu";
	}

	@RequestMapping("/error")
	public String user_menu() {

		return "login-error";
	}
}
