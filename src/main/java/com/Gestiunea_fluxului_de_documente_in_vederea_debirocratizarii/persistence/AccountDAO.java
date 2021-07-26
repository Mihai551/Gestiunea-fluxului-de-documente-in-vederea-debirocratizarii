package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.config.*;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Employee;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Individual;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.LegalEntity;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.EncryptionServices;

import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;

public class AccountDAO {
	@Setter
	@Getter
	@Autowired

	private static SpringJdbcConfig config = new SpringJdbcConfig();

	private static DataSource dataSource = config.dataSource();

	private static JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	public static ResultSet login(SimpleUser theUser) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor",
				"root", "password");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from user where emailAddress='" + theUser.getEmailAddress() + "'");

		return rs;

	}

	public static void SignUpIndividual(Individual theUser) {
		try {
			String salt = EncryptionServices.salt();

			jdbcTemplate.update("INSERT INTO USER VALUES (?, ?, ?, ?)", theUser.getEmailAddress(),
					EncryptionServices.HashPassword(theUser.getPassword(), salt), salt, theUser.getType());

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("INDIVIDUAL");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("pin", theUser.getPin());
			parameters.put("firstName", theUser.getFirstName());
			parameters.put("lastName", theUser.getLastName());
			parameters.put("emailAddress", theUser.getEmailAddress());

			simpleJdbcInsert.execute(parameters);

		}

		catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

	public static void SignUpLegalEntity(LegalEntity theUser) {
		try {

			String salt = EncryptionServices.salt();

			jdbcTemplate.update("INSERT INTO USER VALUES (?, ?, ?, ?)", theUser.getEmailAddress(),
					EncryptionServices.HashPassword(theUser.getPassword(), salt), salt, theUser.getType());

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("INDIVIDUAL");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("country", theUser.getCountry());
			parameters.put("legalEntityName", theUser.getLegalEntityName());
			parameters.put("city", theUser.getCity());
			parameters.put("address", theUser.getAddress());
			parameters.put("emailAddress", theUser.getEmailAddress());

			simpleJdbcInsert.execute(parameters);

		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

	public static void SignUpEmployee(Employee theUser) {
		try {

			String salt = EncryptionServices.salt();

			jdbcTemplate.update("INSERT INTO USER VALUES (?, ?, ?, ?)", theUser.getEmailAddress(),
					EncryptionServices.HashPassword(theUser.getPassword(), salt), salt, theUser.getType());
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("INDIVIDUAL");

			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("pin", theUser.getPin());
			parameters.put("firstName", theUser.getFirstName());
			parameters.put("lastName", theUser.getLastName());
			parameters.put("emailAddress", theUser.getEmailAddress());
			parameters.put("legalEntityEmailAddress", theUser.getLegalEntityEmailAddress());

			simpleJdbcInsert.execute(parameters);

		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();

		}

	}

}
