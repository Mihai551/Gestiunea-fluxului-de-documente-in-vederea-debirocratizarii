package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.config.SpringJdbcConfig;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Doc;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Permission;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;

import lombok.Getter;
import lombok.Setter;

public class DocumentsDAO {

	private static SpringJdbcConfig config = new SpringJdbcConfig();

	private static DataSource dataSource = config.dataSource();

	private static JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	public static void addDocument(DocumentPackage thePackage) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("documents");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ownerEmailAddress", thePackage.getOwnerEmailAddress());
		parameters.put("packageName", thePackage.getPackageName());
		parameters.put("documentName", thePackage.getDocumentName());
		parameters.put("documentContent", thePackage.getDocumentContent());
		simpleJdbcInsert.execute(parameters);

	}

	public static void addPackage(SimplePackage thePackage) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("packages");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ownerEmailAddress", thePackage.getOwnerEmailAddress());
		parameters.put("packageName", thePackage.getPackageName());
		parameters.put("packageDescription", thePackage.getPackageDescription());
		simpleJdbcInsert.execute(parameters);

	}

	public SimplePackage pullSimplePackage(SimplePackage thePackage) {

		try {

			String query = String.format("SELECT * FROM packages WHERE ownerEmailAddress = '%s' AND packageName = '%s'",
					thePackage.getOwnerEmailAddress(), thePackage.getPackageName());

			SimplePackage simplePackage = jdbcTemplate.queryForObject(query, new SimplePackageRowMapper());

			return (SimplePackage) simplePackage;
		} catch (Exception e) {

		}
		return new SimplePackage();

	}

	public static boolean checkExistence(SimplePackage thePackage) {

		try {

			String query = String.format("SELECT * FROM packages WHERE ownerEmailAddress = '%s' AND packageName = '%s'",
					thePackage.getOwnerEmailAddress(), thePackage.getPackageName());

			SimplePackage simplePackage = jdbcTemplate.queryForObject(query, new SimplePackageRowMapper());

			if ((simplePackage.getOwnerEmailAddress().equalsIgnoreCase(thePackage.getOwnerEmailAddress()))
					& (simplePackage.getPackageName().equalsIgnoreCase(thePackage.getPackageName()))) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}

	}

	public static boolean checkDocExistence(DocumentPackage thePackage) {

		try {

			String query = String.format(
					"SELECT * FROM documents WHERE ownerEmailAddress = '%s' AND packageName = '%s' AND documentName = '%s'",
					thePackage.getOwnerEmailAddress(), thePackage.getPackageName(), thePackage.getDocumentName());

			Doc document = jdbcTemplate.queryForObject(query, new DocumentRowMapper());

			if ((document.getOwnerEmailAddress().equalsIgnoreCase(thePackage.getOwnerEmailAddress()))
					& (document.getPackageName().equalsIgnoreCase(thePackage.getPackageName()))
					& (document.getDocumentName().equalsIgnoreCase(thePackage.getDocumentName()))) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}

	}

	public static void addPermission(DocumentPackage thePackage) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("permissions");

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ownerEmailAddress", thePackage.getOwnerEmailAddress());
		parameters.put("packageName", thePackage.getPackageName());
		parameters.put("emailAddress", thePackage.getPermissionEmailAddress());
		parameters.put("permission", thePackage.getPermission());
		simpleJdbcInsert.execute(parameters);
	}

	public static boolean checkPermissionExistence(DocumentPackage thePackage) {

		try {

			String query = String.format(
					"SELECT * FROM permissions WHERE ownerEmailAddress = '%s' AND packageName = '%s' AND emailAddress = '%s' AND permission = '%s'",
					thePackage.getOwnerEmailAddress(), thePackage.getPackageName(),
					thePackage.getPermissionEmailAddress(), thePackage.getPermission());

			Permission thePermission = jdbcTemplate.queryForObject(query, new PermissionRowMapper());

			if ((thePermission.getOwnerEmailAddress().equalsIgnoreCase(thePackage.getOwnerEmailAddress()))
					& (thePermission.getPackageName().equalsIgnoreCase(thePackage.getPackageName()))
					& (thePermission.getEmailAddress().equalsIgnoreCase(thePackage.getPermissionEmailAddress()))
					& (thePermission.getPermission().equalsIgnoreCase(thePackage.getPermission()))) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;

		}

	}
}