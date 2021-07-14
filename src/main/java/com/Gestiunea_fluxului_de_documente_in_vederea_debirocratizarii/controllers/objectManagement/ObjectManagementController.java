package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.objectManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.AccountType;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Individual;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.User;

@Controller
public class ObjectManagementController {

	@RequestMapping("/processForm-Individual")
	public String register(@ModelAttribute("user") Individual theUser, Model theModel) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate("insert into individuals(firstName,lastName,pin,emailAddress,password)values('"
					+ theUser.getFirstName() + "','" + theUser.getLastName() + "','" + theUser.getPin() + "','"
					+ theUser.getEmailAddress() + "','" + theUser.getPassword() + "')");
			// System.out.println("Thank you for register ! Please <a
			// href='index.html'>Login</a> to continue.");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

		theModel.addAttribute("user", theUser);

		return "Individual-confirmation";
	}
}
