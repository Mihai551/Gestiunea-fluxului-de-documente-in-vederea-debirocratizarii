package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.facades.PdfFileSignature;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Doc {

	private String ownerEmailAddress;

	private String packageName;

	private String documentName;

	private String IN_FILE;

	private String action;

	private byte[] documentContent;
	
	private String id;

	private static boolean isNull;

	// private List<String> signedBy;

	// private Document document;
/***
	public static void sign(DocumentsModel documents) {

		List<String> list = DocumentsDAO.pullSignatures(documents);

		String home = System.getProperty("user.home");
		String file = home + "/Downloads/" + documents.getDocumentName() + ".pdf";
		for (String i : list) {

			System.out.println(file);
			PdfFileSignature pdfSign = new PdfFileSignature(file, file);
			Rectangle rect = new Rectangle(100, 100, 200, 100);
			pdfSign.sign(1, false, rect,
					new PKCS7("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + i + "P12.p12", "password"));
			pdfSign.save(file);

		}
	}
***/
	public static byte[] pdfToBlob(String IN_FILE) throws IOException {

		// Convert PDF to BLOB

		try {
			byte[] inFileBytes = Files.readAllBytes(Paths.get(IN_FILE));
			byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(inFileBytes);
			isNull = false;
			return encoded;
		} catch (Exception e) {
			isNull = true;
			return null;
		}
	}

	public static void blobToPdf(byte[] encoded, String fileName) throws Exception {

		// Convert BLOB to PDF and save PDF

		String home = System.getProperty("user.home");
		String OUT_FILE = home + "/Downloads/" + fileName + ".pdf";

		byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(encoded);

		FileOutputStream pdf = new FileOutputStream(OUT_FILE);
		pdf.write(decoded);
		pdf.flush();
		pdf.close();
	}

	public static void openPdf(String fileName) {

		// Open PDF after save

		String home = System.getProperty("user.home");
		String OUT_FILE = home + "/Downloads/" + fileName + ".pdf";

		try {
			File myFile = new File(OUT_FILE);
			Desktop.getDesktop().open(myFile);
		} catch (IOException ex) {
		}
	}

	
}
