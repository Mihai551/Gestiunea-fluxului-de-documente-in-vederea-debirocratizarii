package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Employee;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;

public class UserRowMapper implements RowMapper<SimpleUser> {
    

	@Override
    public SimpleUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        SimpleUser simpleUser = new SimpleUser();
        
        simpleUser.setEmailAddress(rs.getString("emailAddress"));
        simpleUser.setPassword(rs.getString("password"));     
        simpleUser.setSalt(rs.getString("salt"));
        simpleUser.setAccountType(rs.getString("type"));
       

        return simpleUser;
    }
}