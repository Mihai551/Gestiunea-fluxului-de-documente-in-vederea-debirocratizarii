package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.config.SpringJdbcConfig;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Doc;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentsModel;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.PackagesModel;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Permission;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SigningFlow;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimpleUser;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.OtherServices;

import ch.qos.logback.core.net.SyslogOutputStream;

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
		// parameters.put("documentContent", thePackage.getDocumentContent());
		simpleJdbcInsert.execute(parameters);

	}

	public static void addPackage(DocumentPackage simplePackage) {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("packages");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ownerEmailAddress", simplePackage.getOwnerEmailAddress());
		parameters.put("packageName", simplePackage.getPackageName());
		parameters.put("packageDescription", simplePackage.getDescription());
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

	public static boolean checkPackageExistence(DocumentPackage thePackage) {

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

	public static PackagesModel pullPackages(SimpleUser theUser) {

		try {

			String query = "SELECT * FROM packages WHERE ownerEmailAddress = ?";

			List<SimplePackage> packages = new ArrayList<SimplePackage>();

			List<Map<String, Object>> packagesMap = ((jdbcTemplate.queryForList(query, theUser.getEmailAddress())));

			int i = 0;

			while (i < packagesMap.size()) {
				SimplePackage thePackage = new SimplePackage();
				thePackage.setOwnerEmailAddress(packagesMap.get(i).get("ownerEmailAddress").toString());
				thePackage.setPackageName(packagesMap.get(i).get("packageName").toString());
				thePackage.setPackageDescription(packagesMap.get(i).get("packageDescription").toString());
				packages.add(thePackage);
				i++;
			}

			PackagesModel packagesModel = new PackagesModel();
			packagesModel.setList(packages);
			return packagesModel;

		} catch (Exception e) {

			return new PackagesModel();
		}

	}

	public static DocumentsModel pullDocuments(SimplePackage thePackage) {

		try {

			String query = "SELECT * FROM documents WHERE ownerEmailAddress = ? AND packageName = ?";

			List<Doc> documents = new ArrayList<Doc>();

			List<Map<String, Object>> documentsMap = ((jdbcTemplate.queryForList(query,
					thePackage.getOwnerEmailAddress(), thePackage.getPackageName())));

			int i = 0;

			while (i < documentsMap.size()) {
				Doc theDocument = new Doc();
				theDocument.setOwnerEmailAddress(documentsMap.get(i).get("ownerEmailAddress").toString());
				theDocument.setPackageName(documentsMap.get(i).get("packageName").toString());
				theDocument.setDocumentName(documentsMap.get(i).get("documentName").toString());
				theDocument.setDocumentContent((byte[]) (documentsMap.get(i)).get("documentContent"));
				documents.add(theDocument);
				i++;
			}

			DocumentsModel documentsModel = new DocumentsModel();
			documentsModel.setList(documents);
			return documentsModel;

		} catch (Exception e) {

			return new DocumentsModel();
		}

	}

	public static Doc pullDocument(DocumentPackage document) {

		try {

			String query = String.format(
					"SELECT * FROM documents WHERE ownerEmailAddress = '%s' AND packageName = '%s' AND documentName = '%s'",
					document.getOwnerEmailAddress(), document.getPackageName(), document.getDocumentName());

			Doc theDocument = jdbcTemplate.queryForObject(query, new DocumentRowMapper());

			return theDocument;

		} catch (Exception e) {

			return new Doc();
		}

	}

	public static List<String> pullOwnersList(SimpleUser theUser) {

		try {

			String query = "SELECT * FROM permissions WHERE emailAddress = ?";

			List<String> owners = new ArrayList<String>();

			List<Map<String, Object>> packagesMap = ((jdbcTemplate.queryForList(query, theUser.getEmailAddress())));

			for (Map<String, Object> thePackage : packagesMap) {
				String owner = new String();
				owner = thePackage.get("ownerEmailAddress").toString();

				if (owners.contains(owner) == false) {
					owners.add(owner);
				}
			}
			return owners;

		} catch (Exception e) {

			return null;
		}
	}

	public static List<SimplePackage> pullPackagesOfOthers(PackagesModel thePackagesModel) {

		try {

			String query = "SELECT * FROM permissions WHERE ownerEmailAddress = ? AND emailAddress = ?";

			List<SimplePackage> packages = new ArrayList<SimplePackage>();

			List<Map<String, Object>> packagesMap = ((jdbcTemplate.queryForList(query, thePackagesModel.getFromUser(),
					thePackagesModel.getForUser())));

			int i = 0;
			while (i < packagesMap.size()) {
				SimplePackage thePackage = new SimplePackage();
				thePackage.setOwnerEmailAddress(packagesMap.get(i).get("ownerEmailAddress").toString());
				thePackage.setPackageName(packagesMap.get(i).get("packageName").toString());
				thePackage.setPermissionEmailAddress(packagesMap.get(i).get("emailAddress").toString());

				if (!OtherServices.containsPackageName(packages, thePackage.getPackageName())) {
					packages.add(thePackage);
				}

				i++;

			}
			return packages;

		} catch (Exception e) {

			return null;
		}

	}

	public static List<String> checkPermissions(DocumentsModel documents) {

		try {

			String query = "SELECT * FROM permissions WHERE ownerEmailAddress = ? AND emailAddress = ? and packageName = ?";

			List<String> permissions = new ArrayList<String>();

			List<Map<String, Object>> packagesMap = ((jdbcTemplate.queryForList(query, documents.getOwnerEmailAddress(),
					documents.getPermissionEmailAddress(), documents.getPackageName())));

			for (Map<String, Object> x : packagesMap) {
				permissions.add(x.get("permission").toString());

			}

			return permissions;

		} catch (Exception e) {

			return null;
		}

	}

	public static void Sign(DocumentsModel documents, String typeOfSigner) {
		if (typeOfSigner.equalsIgnoreCase("owner")) {

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("signatures");

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ownerEmailAddress", documents.getOwnerEmailAddress());
			parameters.put("packageName", documents.getPackageName());
			parameters.put("documentName", documents.getDocumentName());
			parameters.put("signedBy", documents.getOwnerEmailAddress());
			simpleJdbcInsert.execute(parameters);

		} else {

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("signatures");

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ownerEmailAddress", documents.getOwnerEmailAddress());
			parameters.put("packageName", documents.getPackageName());
			parameters.put("documentName", documents.getDocumentName());
			parameters.put("signedBy", documents.getPermissionEmailAddress());
			simpleJdbcInsert.execute(parameters);
		}

	}

	public static List<String> pullSignatures(DocumentsModel documents) {

		try {
			String query = "SELECT * FROM signatures WHERE ownerEmailAddress = ? and packageName = ? AND documentName = ?";
			List<Map<String, Object>> packagesMap = ((jdbcTemplate.queryForList(query, documents.getOwnerEmailAddress(),
					documents.getPackageName(), documents.getDocumentName())));
			List<String> signers = new ArrayList<String>();
			for (Map<String, Object> thePackage : packagesMap) {
				String signer = new String();
				signer = thePackage.get("signedBy").toString();

				if (signers.contains(signer) == false) {
					signers.add(signer);
				}
			}
			return signers;
		} catch (Exception e) {
			return null;
		}

	}

	public static List<String> pullPermissionsOfPackage(SimplePackage thePackage) {

		try {

			String query = "SELECT emailAddress FROM permissions WHERE ownerEmailAddress = ? AND packageName = ? AND permission = 'Sign'";

			List<String> users = new ArrayList<String>();

			List<Map<String, Object>> Map = ((jdbcTemplate.queryForList(query, thePackage.getOwnerEmailAddress(),
					thePackage.getPackageName())));

			for (Map<String, Object> map : Map) {

				users.add((String) map.get("emailAddress"));

			}
			return users;

		} catch (Exception e) {

			return null;
		}

	}

	public static void addStep(SigningFlow signingflow) {

		String query = "UPDATE permissions SET step = ? WHERE ownerEmailAddress = ? AND packageName = ? AND emailAddress =?";
		jdbcTemplate.update(query, signingflow.getStep(), signingflow.getOwnerEmailAddress(),
				signingflow.getPackageName(), signingflow.getUser());
	}

	public static void setSigningFlowEnable(String enable_disable, SimplePackage thePackage) {

		String query = "UPDATE packages SET signingFlowEnable = ? WHERE ownerEmailAddress = ? AND packageName = ?";
		jdbcTemplate.update(query, enable_disable, thePackage.getOwnerEmailAddress(), thePackage.getPackageName());
	}

	public static String setSigningFlowEnableFromDB(SimplePackage thePackage) {
		String query = "SELECT signingFlowEnable FROM packages WHERE ownerEmailAddress = ? AND packageName = ?";

		List<Map<String, Object>> packagesMap = (jdbcTemplate.queryForList(query, thePackage.getOwnerEmailAddress(),
				thePackage.getPackageName()));
		try {
			return packagesMap.get(0).get("signingFlowEnable").toString();
		} catch (Exception e) {
			return null;
		}
	}

	// DE VERIFICAT DE AICI

	public static List<Integer> pullSteps(SimplePackage thePackage) {
		System.out.println("pullSteps1");
		String query = "SELECT step FROM permissions WHERE ownerEmailAddress = ? AND packageName = ? AND permission = 'Sign'";
		System.out.println("pullSteps2");
		List<Map<String, Object>> packagesMap = (jdbcTemplate.queryForList(query, thePackage.getOwnerEmailAddress(),
				thePackage.getPackageName()));
		System.out.println("pullSteps3");
		List<Integer> steps = new ArrayList<Integer>();
		System.out.println("pullSteps4");
		for (Map<String, Object> map : packagesMap) {
			System.out.println("pullSteps in for before if");
			if (!steps.contains(Integer.parseInt(map.get("step").toString()))) {

				System.out.println("pullSteps in for IN if");

				steps.add(Integer.parseInt(map.get("step").toString()));
				System.out.println(Integer.parseInt(map.get("step").toString()));
				System.out.println("pullSteps in for IN if AFTER add");

				System.out.println("pullSteps5");
			}
			System.out.println("pullSteps6");
		}
		System.out.println("pullSteps7");

		Collections.sort(steps);
		for (int step : steps) {
			System.out.println(step);
		}
		return steps;
	}

	public static int signatureStep(DocumentsModel documents, String signature) {
		System.out.println("signatureStep 1");
		String query = "SELECT step FROM permissions WHERE ownerEmailAddress = ? AND packageName = ? AND emailAddress = ? AND permission = 'Sign'";
		System.out.println("signatureStep 2");
		List<Map<String, Object>> permissionsMap = (jdbcTemplate.queryForList(query, documents.getOwnerEmailAddress(),
				documents.getPackageName(), signature));
		System.out.println("signatureStep 3");
		System.out.println(Integer.parseInt(permissionsMap.get(0).get("step").toString()));
		System.out.println("signatureStep 4");
		return Integer.parseInt(permissionsMap.get(0).get("step").toString());

	}

	public static List<String> permissionsByStep(DocumentsModel documents, int step) {

		String query = "SELECT emailAddress FROM permissions WHERE ownerEmailAddress = ? AND packageName = ? AND step = ? AND permission = 'Sign'";

		List<Map<String, Object>> permissionsMap = (jdbcTemplate.queryForList(query, documents.getOwnerEmailAddress(),
				documents.getPackageName(), step));

		List<String> permissionsByStep = new ArrayList();

		for (Map<String, Object> map : permissionsMap) {

			permissionsByStep.add((String) map.get("emailAddress"));

		}

		return permissionsByStep;

	}

}