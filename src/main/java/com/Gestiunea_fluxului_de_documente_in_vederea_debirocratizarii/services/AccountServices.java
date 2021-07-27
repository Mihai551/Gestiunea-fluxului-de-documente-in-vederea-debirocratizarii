package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.AccountDAO;;

public class AccountServices {

	public static boolean login(SimpleUser theUser) throws ClassNotFoundException, SQLException {

		SimpleUser dbUser = AccountDAO.login(theUser);
		try {
			System.out.println(dbUser.toString());
			System.out.println("account services, salt string " + dbUser.getSalt());

			System.out.println("account services, database password " + dbUser.getPassword());

			System.out.println("account services, encrypted password"
					+ EncryptionServices.HashPassword(theUser.getPassword(), dbUser.getSalt()));

			if (dbUser.getPassword().equals(EncryptionServices.HashPassword(theUser.getPassword(), dbUser.getSalt()))) {
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
