package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.config.SpringJdbcConfig;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;

import lombok.Getter;
import lombok.Setter;

public class DocumentsDAO {

	private static SpringJdbcConfig config = new SpringJdbcConfig();

	private static DataSource dataSource = config.dataSource();

	private static JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	static SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("documents");

	public static void addDocument(DocumentPackage thePackage) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("emailAddress", thePackage.getOwnerEmailAddress());
		parameters.put("packageName", thePackage.getPackageName());
		parameters.put("documentName", thePackage.getDocumentName());
		parameters.put("documentContent", thePackage.getDocumentContent());
		simpleJdbcInsert.execute(parameters);

		// Map<String, byte[]> parameterBlob = new HashMap<String, byte[]>();
		// parameterBlob.put("documentContent", thePackage.getDocumentContent());

		// simpleJdbcInsert.execute(parameterBlob);
	}

}
