package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;


public class SimplePackageRowMapper implements RowMapper<SimplePackage> {
    

	@Override
    public SimplePackage mapRow(ResultSet rs, int rowNum) throws SQLException {
        SimplePackage simplePackage = new SimplePackage();
        
        simplePackage.setOwnerEmailAddress(rs.getString("ownerEmailAddress"));
        simplePackage.setPackageName(rs.getString("packageName"));     
        simplePackage.setPackageDescription(rs.getString("packageDescription")); 
        simplePackage.setSigningFlowEnable(rs.getString("signingFlowEnable"));
        System.out.println("rowmapper " + simplePackage.getSigningFlowEnable());
        return simplePackage;
    }
}