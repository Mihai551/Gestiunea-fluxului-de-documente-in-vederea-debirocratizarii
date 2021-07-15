package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;

public class Login {
	
	public static boolean login (SimpleUser theUser) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiunea_documentelor","root","password");
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery("select * from "+theUser.getAccountType()+" where emailAddress='"+theUser.getEmailAddress()+"' and password='"+theUser.getPassword()+"'");
		try{
		rs.next();
		if(rs.getString("password").equals(theUser.getPassword())&&rs.getString("emailAddress").equals(theUser.getEmailAddress()))
		{
		System.out.println("Welcome " +theUser.getEmailAddress());
		
		
		return true;
		}
		else{
		System.out.println("Invalid password or username.");
			return false;
		}
		}
		catch (Exception e) {
		e.printStackTrace();
		return false;
		}

	}
	
}
