package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JOptionPane;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentsModel;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.aspose.pdf.PKCS7;
import com.aspose.pdf.facades.PdfFileSignature;
import java.util.Collection;

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

			if (!thePackage.getOwnerEmailAddress().equalsIgnoreCase(thePackage.getPermissionEmailAddress())) {

				String text = String.format("Utilizatorul %s v-a acordat permisiunea '%s' la pachetul  %s .",
						thePackage.getOwnerEmailAddress(), thePackage.getPermission(), thePackage.getPackageName());
				try {
					EmailService email = new EmailService(thePackage.getPermissionEmailAddress(), "new permission",
							text);
					email.start();
				} catch (Exception e) {

				}

			}
		}
	}

	public static String Sign(DocumentsModel documents, String typeOfSigner) {

		System.out.println(
				"sign-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
		System.out.println("Actual step from Sign  " + DocumentServices.actualStep(documents));

		List<String> signers = DocumentsDAO.pullSignatures(documents);
		for (String s : signers) {
			System.out.println(s + " -signers");
		}

		if ((!signers.contains(documents.getOwnerEmailAddress())) && typeOfSigner.equalsIgnoreCase("owner")) {

			try {
				if (new FileInputStream("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + documents.getOwnerEmailAddress()
						+ "P12.p12") == null) {
					System.out.println("utilziatorul nu are certificat");

					return "You need a digital certificate.";
				}
			} catch (Exception e) {
				System.out.println("utilziatorul nu are certificat");

				return "You need a digital certificate.";
			}

			SimplePackage thePackage = new SimplePackage();
			thePackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
			thePackage.setPackageName(documents.getPackageName());
			thePackage.setSigningFlowEnable(DocumentsDAO.setSigningFlowEnableFromDB(thePackage));

			List<String> allowedSigners = DocumentsDAO.permissionsByStep(documents,
					DocumentServices.actualStep(documents));

			if (Integer.parseInt(thePackage.getSigningFlowEnable()) == 1) {
				if (typeOfSigner.equalsIgnoreCase("owner")
						&& allowedSigners.contains(documents.getOwnerEmailAddress())) {
					DocumentsDAO.Sign(documents, typeOfSigner);

					return "Successfully signed.";
				} else {
					System.out.println("WRONG STEP!");

					return "Wrong step.";
				}

			}

			DocumentsDAO.Sign(documents, typeOfSigner);

			System.out.println(DocumentsDAO.pullSignatures(documents));

			return "Successfully signed.";
		}
		System.out.println(documents.getPermissionEmailAddress() + "   if ((!signers.contains(documents.getPermissionEmailAddress()))");
		if ((!signers.contains(documents.getPermissionEmailAddress())) && !(typeOfSigner.equalsIgnoreCase("owner"))) {
			System.out.println(documents.getPermissionEmailAddress() + "   if ((!signers.contains(documents.getPermissionEmailAddress()))");
			SimplePackage thePackage = new SimplePackage();
			thePackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
			thePackage.setPackageName(documents.getPackageName());
			thePackage.setSigningFlowEnable(DocumentsDAO.setSigningFlowEnableFromDB(thePackage));

			List<String> allowedSigners = DocumentsDAO.permissionsByStep(documents,
					DocumentServices.actualStep(documents));

			try {
				if (new FileInputStream("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\"
						+ documents.getPermissionEmailAddress() + "P12.p12") == null) {
					System.out.println("utilziatorul nu are certificat");

					return "You need a digital certificate.";
				}
			} catch (Exception e) {

				return "You need a digital certificate.";
			}

			if (Integer.parseInt(thePackage.getSigningFlowEnable()) == 1) {

				if (!typeOfSigner.equalsIgnoreCase("owner")
						&& allowedSigners.contains(documents.getPermissionEmailAddress())) {
					DocumentsDAO.Sign(documents, typeOfSigner);

					String text = String.format("Utilizatorul %s a semnat documentul '%s' din pachetul  %s .",
							documents.getPermissionEmailAddress(), documents.getDocumentName(),
							documents.getPackageName());
					try {
						EmailService email = new EmailService(documents.getOwnerEmailAddress(), "new signature", text);
						email.start();
					} catch (Exception e) {
						// System.out.println("utilziatorul nu are certificat");

					}

					return "Successfully signed.";

				} else {
					System.out.println("WRONG STEP!");

					return "Wrong step.";
				}
			}

			DocumentsDAO.Sign(documents, typeOfSigner);
			System.out.println(DocumentsDAO.pullSignatures(documents));

			String text = String.format("Utilizatorul %s a semnat documentul '%s' din pachetul  %s .",
					documents.getPermissionEmailAddress(), documents.getDocumentName(), documents.getPackageName());
			try {
				EmailService email = new EmailService(documents.getOwnerEmailAddress(), "new signature", text);
				email.start();
			} catch (Exception e) {
				System.out.println("utilziatorul nu are certificat");

			}

			return "Successfully signed.";
		}

		return "You have already signed.";

	}

	public static void enableOrDisable(SimplePackage thePackage) {

		try {

			if (thePackage.getSigningFlowEnable().equalsIgnoreCase("1")) {
				thePackage.setSigningFlowEnable("0");
				DocumentsDAO.setSigningFlowEnable(thePackage.getSigningFlowEnable(), thePackage);
			} else {
				thePackage.setSigningFlowEnable("1");
				DocumentsDAO.setSigningFlowEnable(thePackage.getSigningFlowEnable(), thePackage);

			}
		} catch (Exception e) {
			System.out.println("EROARE");
			thePackage.setSigningFlowEnable("1");
			DocumentsDAO.setSigningFlowEnable(thePackage.getSigningFlowEnable(), thePackage);

		}

	}

	

	public static int actualStep(DocumentsModel documents) {

		int actualStep;

		List<String> signatures = DocumentsDAO.pullSignatures(documents); // get signatures
		SimplePackage thePackage = new SimplePackage();
		thePackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
		thePackage.setPackageName(documents.getPackageName());
		List<Integer> steps = DocumentsDAO.pullSteps(thePackage); // get list of steps

		String lastSignature;
		if (signatures.size() > 0) {
			lastSignature = signatures.get(signatures.size() - 1); // last signature
		} else {
			for (Integer i : steps) {
				System.out.println(i);
			}
			System.out.println(steps.get(0) + "  " + steps.get(1));
			return steps.get(0);
		}

		int lastSignerStep = DocumentsDAO.signatureStep(documents, lastSignature); // step of last signature
		List<String> permissionsByStep = DocumentsDAO.permissionsByStep(documents, lastSignerStep); // users at the step
		if (signatures.containsAll(permissionsByStep)) {
			try {
				actualStep = steps.get((steps.indexOf(lastSignerStep) + 1)); // next step
			} catch (Exception e) {
				actualStep = steps.get((steps.indexOf(lastSignerStep)));


			}

		} else {

			actualStep = steps.get((steps.indexOf(lastSignerStep))); // this step
		}
		return actualStep;

	}

}
