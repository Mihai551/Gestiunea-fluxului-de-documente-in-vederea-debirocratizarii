package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;

public class DocumentServices {

	public static void pushSimplePackageIfNotExist(SimplePackage simplePackage) {

		boolean exists = DocumentsDAO.checkExistence(simplePackage);
		System.out.println("EXISTS: " + exists);
		if (exists == false) {

			DocumentsDAO.addPackage(simplePackage);

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
		
		if ( DocumentsDAO.checkDocExistence(thePackage) == false ) {
			
			DocumentsDAO.addDocument(thePackage);
			
		}
		
	}
}
