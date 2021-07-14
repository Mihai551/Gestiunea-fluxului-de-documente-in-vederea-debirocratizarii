package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;

public class SignUp {

	public static void Individual(Individual theUser) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate("insert into individuals(firstName,lastName,pin,emailAddress,password)values('"
					+ theUser.getFirstName() + "','" + theUser.getLastName() + "','" + theUser.getPin() + "','"
					+ theUser.getEmailAddress() + "','" + theUser.getPassword() + "')");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

	public static void LegalEntity(LegalEntity theUser) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate(
					"insert into legal_entities(legalEntityName,emailAddress,country,city,address,password)values('"
							+ theUser.getLegalEntityName() + "','" + theUser.getEmailAddress() + "','"
							+ theUser.getCountry() + "','" + theUser.getCity() + "','" + theUser.getAddress() + "','"
							+ theUser.getPassword() + "')");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

	public static void Employee(Employee theUser) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate(
					"insert into employees(legalEntityName,firstName,lastName,pin,emailAddress,password,inviteCode)values('"
							+ theUser.getLegalEntityName() + "','" + theUser.getFirstName() + "','"
							+ theUser.getLastName() + "','" + theUser.getPin() + "','" + theUser.getEmailAddress()
							+ "','" + theUser.getPassword() + "','" + theUser.getInviteCode() + "')");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

}
