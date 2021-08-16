package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Doc;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentsModel;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.facades.PdfFileSignature;
import com.aspose.words.DigitalSignature;
import com.aspose.words.Document;

public class SignatureServices {

	public static void digitalSign(String ownerEmailAddress, String packageName, String documentName, String signer) throws FileNotFoundException, Exception {

		DocumentPackage auxDoc = new DocumentPackage();
		auxDoc.setDocumentName(documentName);
		auxDoc.setOwnerEmailAddress(ownerEmailAddress);
		auxDoc.setPackageName(packageName);
			
		Doc doc = DocumentsDAO.pullDocument(auxDoc);
		
		String OUT_FILE ="C:\\Users\\Mihai\\Desktop\\Documents\\" + doc.getId() +".pdf";

		// ADD SIGNATURES

		com.aspose.pdf.License license = new com.aspose.pdf.License();
		license.setLicense(new java.io.FileInputStream(
				"C:\\Users\\Mihai\\eclipse-jee-2018-09-win32-x86_64-workspace\\Gestiunea-fluxului-de-documente-in-vederea-debirocratizarii\\lib\\Aspose.PDF.Java.lic"));

		

			System.out.println(OUT_FILE);
			@SuppressWarnings("deprecation")
			PdfFileSignature pdfSign = new PdfFileSignature(OUT_FILE, OUT_FILE);
			Rectangle rect = new Rectangle(100, 100, 200, 100);
			pdfSign.sign(1, false, rect,
					new PKCS7("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + signer + "P12.p12", "password"));
			pdfSign.save(OUT_FILE);
		

	}

	public static List<String> digitalSignatureValidation(DocumentsModel documents) throws Exception {

		com.aspose.pdf.License license = new com.aspose.pdf.License();
		license.setLicense(new java.io.FileInputStream(
				"C:\\Users\\Mihai\\eclipse-jee-2018-09-win32-x86_64-workspace\\Gestiunea-fluxului-de-documente-in-vederea-debirocratizarii\\lib\\Aspose.PDF.Java.lic"));

		String home = System.getProperty("user.home");
		String _dataDir = home + "/Downloads/";

		List<String> validSignatures = new ArrayList<String>();

		List<String> list = DocumentsDAO.pullSignatures(documents);
		int index = 1;

		PdfFileSignature pdfSign = new PdfFileSignature();
		pdfSign.bindPdf(_dataDir + documents.getDocumentName() + ".pdf");
		for (String i : list) {

			if (pdfSign.verifySignature("Signature" + index)) {
				System.out.println(i + "'s signature is valid");

				validSignatures.add(i);

			} else {
				System.out.println(i + "'s signature is not valid");
			}

			index++;
		}
		pdfSign.close();
		return validSignatures;
	}

}
