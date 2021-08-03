package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;

public class PackagesRowMapper implements RowMapper<List<SimplePackage>> {

	public List<SimplePackage> mapRow(ResultSet rs, int rowNum) throws SQLException {
		PackagesModel packages = new PackagesModel();
		

		while (rs.next()) {
			if (rs.getString("ownerEmailAddress") != null) {
				SimplePackage thePackage = new SimplePackage();
				thePackage.setOwnerEmailAddress(rs.getString("ownerEmailAddress"));
				thePackage.setPackageName(rs.getString("packageName"));
				thePackage.setPackageDescription(rs.getString("description"));

				packages.list.add(thePackage);
				
				for (SimplePackage x : packages.list) {
				System.out.println(x);
				}
			}
		}

		return packages.list;

	}
}
