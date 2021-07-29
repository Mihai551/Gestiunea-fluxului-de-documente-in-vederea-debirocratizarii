package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;

public class DocumentServices {

	public static void addPackage(DocumentPackage thepackage) {

		if (DocumentsDAO.checkPackageExistence(thepackage) == false && thepackage.getPackageName() != null) {

			DocumentsDAO.addPackage(thepackage);

		}

	}

	public static SimplePackage DocumentPackageToSimplePackage(DocumentPackage thePackage) {

		SimplePackage simplePackage = new SimplePackage();
		simplePackage.setOwnerEmailAddress(thePackage.getOwnerEmailAddress());
		simplePackage.setPackageName(thePackage.getPackageName());
		simplePackage.setPackageDescription(thePackage.getDescription());

		return simplePackage;

	}

	public static void addDocument(DocumentPackage thePackage) {

		if (DocumentsDAO.checkDocExistence(thePackage) == false && thePackage.getDocumentContent() != null) {

			DocumentsDAO.addDocument(thePackage);

		}

	}

	public static void addPermission(DocumentPackage thePackage) {

		if (DocumentsDAO.checkPermissionExistence(thePackage) == false && thePackage.getPermission() != null) {

			DocumentsDAO.addPermission(thePackage);

		}
	}
}
