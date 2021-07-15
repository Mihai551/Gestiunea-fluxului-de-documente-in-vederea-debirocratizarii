package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.objectManagement;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.Login;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.SignUp;

@Controller
public class ObjectManagementController {

	@RequestMapping("/processForm-Individual")
	public String register(@ModelAttribute("user") Individual theUser, Model theModel) {

		SignUp.Individual(theUser);

		theModel.addAttribute("user", theUser);

		return "forward:/individual-confirmation";

	}

	@RequestMapping("/processForm-legal-entity")
	public String register(@ModelAttribute("user") LegalEntity theUser, Model theModel) {

		SignUp.LegalEntity(theUser);

		theModel.addAttribute("user", theUser);

		return "forward:/legal-entity-confirmation";
	}

	@RequestMapping("/processForm-employee")
	public String register(@ModelAttribute("user") Employee theUser, Model theModel) {

		SignUp.Employee(theUser);

		theModel.addAttribute("user", theUser);

		return "forward:/employee-confirmation";
	}

	@RequestMapping("/processForm-login")
	public String login(@ModelAttribute("SimpleUser") SimpleUser theUser, Model theModel) throws ClassNotFoundException, SQLException {
		
		if (Login.login(theUser)) {
			
			theModel.addAttribute("SimpleUser", theUser);
			
			return "forward:/user-menu";
		} 
		else {
			
		return "forward:/error";

		}
	}

	
	@RequestMapping("/logout")
	public String logout() {
		
		return "forward:/";
	}
	
}

