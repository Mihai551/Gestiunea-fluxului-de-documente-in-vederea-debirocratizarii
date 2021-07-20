package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.AccountDAO;;

public class AccountServices {

	public static boolean login(SimpleUser theUser) throws ClassNotFoundException, SQLException {

		ResultSet rs = AccountDAO.login(theUser);
		try {
			rs.next();
			String salt = rs.getString("salt");

			System.out.println("account services, salt string " + rs.getString("salt"));

			System.out.println("account services, database password " + rs.getString("password"));

			System.out.println("account services, encrypted password"
					+ EncryptionServices.HashPassword(theUser.getPassword(), salt));

			if (rs.getString("password").equals(EncryptionServices.HashPassword(theUser.getPassword(), salt))
					&& rs.getString("emailAddress").equals(theUser.getEmailAddress())) {
				System.out.println("Welcome " + theUser.getEmailAddress());

				return true;
			} else {
				System.out.println("Invalid password or username.");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
