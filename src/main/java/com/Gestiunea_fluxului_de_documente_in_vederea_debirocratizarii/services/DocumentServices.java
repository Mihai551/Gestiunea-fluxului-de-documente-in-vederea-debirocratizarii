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

	public static boolean addDocument(DocumentPackage thePackage) {

		if (DocumentsDAO.checkDocExistence(thePackage) == false && thePackage.getPackageName() != null
				&& thePackage.getOwnerEmailAddress() != null && thePackage.getDocumentName() != null) {

			DocumentsDAO.addDocument(thePackage);
			return true;
		} else
			return false;

	}

	public static void addPermission(DocumentPackage thePackage) {

		if (DocumentsDAO.checkPermissionExistence(thePackage) == false && thePackage.getPermission() != null
				&& thePackage.getPackageName() != null && thePackage.getPermissionEmailAddress() != null) {

			DocumentsDAO.addPermission(thePackage);

			String text = String.format("Utilizatorul %s v-a acordat permisiunea '%s' la pachetul  %s .",
					thePackage.getOwnerEmailAddress(), thePackage.getPermission(), thePackage.getPackageName());
			try {
				EmailService email = new EmailService(thePackage.getPermissionEmailAddress(), "new permission", text);
				email.start();
			} catch (Exception e) {

			}

		}
	}

	public static boolean Sign(DocumentsModel documents, String typeOfSigner) {

		List<String> signers = DocumentsDAO.pullSignatures(documents);
		if ((!signers.contains(documents.getOwnerEmailAddress())) && typeOfSigner.equalsIgnoreCase("owner")) {

			DocumentsDAO.Sign(documents, typeOfSigner);

			System.out.println(DocumentsDAO.pullSignatures(documents));

			return true;
		}

		if ((!signers.contains(documents.getPermissionEmailAddress())) && !(typeOfSigner.equalsIgnoreCase("owner"))) {

			DocumentsDAO.Sign(documents, typeOfSigner);
			System.out.println(DocumentsDAO.pullSignatures(documents));

			String text = String.format("Utilizatorul %s a semnat documentul '%s' din pachetul  %s .",
					documents.getPermissionEmailAddress(), documents.getDocumentName(), documents.getPackageName());
			try {
				EmailService email = new EmailService(documents.getOwnerEmailAddress(), "new signature", text);
				email.start();
			} catch (Exception e) {

			}
			return true;
		}

		return false;

	}

}
