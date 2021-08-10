package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.List;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentsModel;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.facades.PdfFileSignature;

public class DocumentServices {

	public static void addPackage(DocumentPackage thepackage) {

		if (DocumentsDAO.checkPackageExistence(thepackage) == false && thepackage.getPackageName() != null) {

			DocumentsDAO.addPackage(thepackage);

		}

	}

	public static void addDocument(DocumentPackage thePackage) {

		if (DocumentsDAO.checkDocExistence(thePackage) == false && thePackage.getPackageName() != null
				&& thePackage.getDocumentContent() != null && thePackage.getDocumentName() != null) {

			DocumentsDAO.addDocument(thePackage);

		}

	}

	public static void addPermission(DocumentPackage thePackage) {

		if (DocumentsDAO.checkPermissionExistence(thePackage) == false && thePackage.getPermission() != null
				&& thePackage.getPackageName() != null && thePackage.getPermissionEmailAddress() != null) {

			DocumentsDAO.addPermission(thePackage);

		}
	}

	public static void Sign(DocumentsModel documents, String typeOfSigner) {

		List<String> signers = DocumentsDAO.pullSignatures(documents);
		if ((!signers.contains(documents.getOwnerEmailAddress())) && typeOfSigner.equalsIgnoreCase("owner")) {

			DocumentsDAO.Sign(documents, typeOfSigner);
			System.out.println(DocumentsDAO.pullSignatures(documents));
		}

		if ((!signers.contains(documents.getPermissionEmailAddress())) && !(typeOfSigner.equalsIgnoreCase("owner"))) {

			DocumentsDAO.Sign(documents, typeOfSigner);
			System.out.println(DocumentsDAO.pullSignatures(documents));
		}

	}

	
}
