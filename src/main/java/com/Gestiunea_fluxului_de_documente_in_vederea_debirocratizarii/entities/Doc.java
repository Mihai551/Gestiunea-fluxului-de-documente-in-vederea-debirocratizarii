package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Doc {

	private String ownerEmailAddress;

	private String packageName;

	private String documentName;
	
	private String IN_FILE;

	// private List<String> signedBy;

	// private Document document;

	public static byte[] pdfToBlob(String IN_FILE) throws IOException {

		// Convert PDF to BLOB

		byte[] inFileBytes = Files.readAllBytes(Paths.get(IN_FILE));
		byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(inFileBytes);
		return encoded;
	}

	public static void blobToPdf(byte[] encoded, String fileName) throws IOException {

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
