package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Permission;

public class PermissionRowMapper implements RowMapper<Permission> {

	@Override
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
		Permission permission = new Permission();

		permission.setOwnerEmailAddress(rs.getString("ownerEmailAddress"));
		permission.setPackageName(rs.getString("packageName"));
		permission.setEmailAddress(rs.getString("emailAddress"));
		permission.setPermission(rs.getString("permission"));

		return permission;
	}
}
