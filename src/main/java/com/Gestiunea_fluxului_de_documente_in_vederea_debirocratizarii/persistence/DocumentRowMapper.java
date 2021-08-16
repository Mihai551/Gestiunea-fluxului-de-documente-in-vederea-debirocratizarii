package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Doc;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;

public class DocumentRowMapper implements RowMapper<Doc> {

	@Override
	public Doc mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doc document = new Doc();

		document.setOwnerEmailAddress(rs.getString("ownerEmailAddress"));
		document.setPackageName(rs.getString("packageName"));
		document.setDocumentName(rs.getString("documentName"));
		document.setId(rs.getString("id"));

		return document;
	}
}
