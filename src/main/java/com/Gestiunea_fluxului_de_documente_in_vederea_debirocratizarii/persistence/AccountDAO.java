package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Employee;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Individual;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.LegalEntity;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.EncryptionServices;

public class AccountDAO {

	public static ResultSet login(SimpleUser theUser) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor",
				"root", "password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from " + theUser.getAccountType() + " where emailAddress='"
				+ theUser.getEmailAddress() + "'");

		return rs;

	}

	public static void SignUpIndividual(Individual theUser) {
		try {
			String salt = new String();
			salt = EncryptionServices.salt();
			System.out.println("SignUpIndividual salt String " + salt);

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate("insert into individual(firstName,lastName,pin,emailAddress,password,salt)values('"
					+ theUser.getFirstName() + "','" + theUser.getLastName() + "','" + theUser.getPin() + "','"
					+ theUser.getEmailAddress() + "','" + EncryptionServices.HashPassword(theUser.getPassword(), salt)
					+ "','" + salt + "')");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

	public static void SignUpLegalEntity(LegalEntity theUser) {
		try {

			String salt = EncryptionServices.salt();

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate(
					"insert into legal_entity(legalEntityName,emailAddress,country,city,address,password,salt)values('"
							+ theUser.getLegalEntityName() + "','" + theUser.getEmailAddress() + "','"
							+ theUser.getCountry() + "','" + theUser.getCity() + "','" + theUser.getAddress() + "','"
							+ EncryptionServices.HashPassword(theUser.getPassword(), salt) + "','" + salt + "')");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

	public static void SignUpEmployee(Employee theUser) {
		try {

			String salt = EncryptionServices.salt();

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor", "root",
					"password");
			Statement st = conn.createStatement();
			int i = st.executeUpdate(
					"insert into employee(legalEntityName,firstName,lastName,pin,emailAddress,password,salt,inviteCode)values('"
							+ theUser.getLegalEntityName() + "','" + theUser.getFirstName() + "','"
							+ theUser.getLastName() + "','" + theUser.getPin() + "','" + theUser.getEmailAddress()
							+ "','" + EncryptionServices.HashPassword(theUser.getPassword(), salt) + "','" + salt
							+ "','" + theUser.getInviteCode() + "')");
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

}
