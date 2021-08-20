package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

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

	public static boolean Sign(DocumentsModel documents, String typeOfSigner) {
		System.out.println(
				"sign-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
		System.out.println("Actual step from Sign  " + DocumentServices.actualStep(documents));

		List<String> signers = DocumentsDAO.pullSignatures(documents);
		if ((!signers.contains(documents.getOwnerEmailAddress())) && typeOfSigner.equalsIgnoreCase("owner")) {

			try {
				if (new FileInputStream("C:\\Program Files\\Java\\jdk-15.0.2\\bin\\" + documents.getOwnerEmailAddress()
						+ "P12.p12") == null) {
					System.out.println("utilziatorul nu are certificat");
					return false;
				}
			} catch (Exception e) {
				System.out.println("utilziatorul nu are certificat");
				return false;
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
					return true;
				} else {
					System.out.println("WRONG STEP!");
					return false;
				}

			}

			DocumentsDAO.Sign(documents, typeOfSigner);

			System.out.println(DocumentsDAO.pullSignatures(documents));

			return true;
		}

		if ((!signers.contains(documents.getPermissionEmailAddress())) && !(typeOfSigner.equalsIgnoreCase("owner"))) {

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
					return false;
				}
			} catch (Exception e) {
				return false;
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
						System.out.println("utilziatorul nu are certificat");

					}

					return true;

				} else {
					System.out.println("WRONG STEP!");
					return false;
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
			return true;
		}

		return false;

	}

	public static void enableOrDisable(SimplePackage thePackage) {

		try {
			System.out.println(thePackage.getSigningFlowEnable() + " AICI");

			if (thePackage.getSigningFlowEnable().equalsIgnoreCase("1")) {
				System.out.println("IF1");
				thePackage.setSigningFlowEnable("0");
				DocumentsDAO.setSigningFlowEnable(thePackage.getSigningFlowEnable(), thePackage);
				System.out.println("IF2");
			} else {
				System.out.println("ELSE1");
				thePackage.setSigningFlowEnable("1");
				DocumentsDAO.setSigningFlowEnable(thePackage.getSigningFlowEnable(), thePackage);
				System.out.println("ELSE2");

			}
		} catch (Exception e) {
			System.out.println("EROARE");
			thePackage.setSigningFlowEnable("1");
			DocumentsDAO.setSigningFlowEnable(thePackage.getSigningFlowEnable(), thePackage);

		}

	}

	// DE VERIFICAT DE AICI

	public static int actualStep(DocumentsModel documents) {
		System.out.println("ACTUAL STEP 1");

		int actualStep;

		List<String> signatures = DocumentsDAO.pullSignatures(documents); // get signatures
		System.out.println("ACTUAL STEP 2");
		SimplePackage thePackage = new SimplePackage();
		thePackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
		thePackage.setPackageName(documents.getPackageName());
		List<Integer> steps = DocumentsDAO.pullSteps(thePackage); // get list of steps
		System.out.println("ACTUAL STEP 3");

		String lastSignature;
		if (signatures.size() > 0) {
			lastSignature = signatures.get(signatures.size() - 1); // last signature
			System.out.println("ACTUAL STEP 4");
		} else {
			System.out.println("ACTUAL STEP ELSE");
			for (Integer i : steps) {
				System.out.println(i);
			}
			System.out.println(steps.get(0) + "  " + steps.get(1));
			return steps.get(0);
		}

		int lastSignerStep = DocumentsDAO.signatureStep(documents, lastSignature); // step of last signature
		System.out.println("ACTUAL STEP 5");
		List<String> permissionsByStep = DocumentsDAO.permissionsByStep(documents, lastSignerStep); // users at the step
		System.out.println("ACTUAL STEP 6");
		if (signatures.containsAll(permissionsByStep)) {
			System.out.println("ACTUAL STEP 7");
			try {
				actualStep = steps.get((steps.indexOf(lastSignerStep) + 1)); // next step
				System.out.println("ACTUAL STEP 8");
			} catch (Exception e) {
				System.out.println("ACTUAL STEP 9");
				actualStep = actualStep = steps.get((steps.indexOf(lastSignerStep)));

				System.out.println("ACTUAL STEP 10");

			}

		} else {
			System.out.println("ACTUAL STEP 11");

			actualStep = steps.get((steps.indexOf(lastSignerStep))); // this step
			System.out.println("ACTUAL STEP 12");
		}
		System.out.println("ACTUAL STEP 13");
		return actualStep;

	}

}
