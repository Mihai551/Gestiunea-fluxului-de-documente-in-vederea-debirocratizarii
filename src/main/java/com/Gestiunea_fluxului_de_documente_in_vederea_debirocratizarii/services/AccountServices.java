package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.AccountDAO;;

public class AccountServices {

	public static boolean login(SimpleUser theUser) throws ClassNotFoundException, SQLException {

		ResultSet rs = AccountDAO.login(theUser);
		try {
			rs.next();
			if (rs.getString("password").equals(theUser.getPassword())
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
