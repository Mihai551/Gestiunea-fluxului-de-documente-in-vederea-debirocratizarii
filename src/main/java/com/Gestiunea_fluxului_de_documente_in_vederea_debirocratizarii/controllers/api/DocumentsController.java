package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.api;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.DocumentServices;

@Controller
public class DocumentsController {

	@RequestMapping("/processForm-new-package")
	public String addPackage(@ModelAttribute("SimpleUser") SimpleUser theUser,
			@ModelAttribute("package") DocumentPackage thePackage, Model theModel, BindingResult result)
			throws IOException {

		theModel.addAttribute("SimpleUser", theUser);

		byte[] encoded = Doc.pdfToBlob(thePackage.getIN_FILE());
		thePackage.setDocumentContent(encoded);

		// add document
		DocumentServices.addDocument(thePackage);

		// add simple package
		DocumentServices.addPackage(thePackage);

		// add permission
		DocumentServices.addPermission(thePackage);

		return "forward:/new-package-of-documents";

	}

	@RequestMapping("/my-document")
	public String viewDoc(@ModelAttribute("documents") DocumentsModel documents, Model theModel) throws IOException {
		if (documents.getAction().equalsIgnoreCase("View")) {
			Doc theDocument = DocumentsDAO.pullDocument(documents);
			System.out.println(theDocument.getDocumentContent());
			System.out.println(theDocument.getDocumentName());
			Doc.blobToPdf(theDocument.getDocumentContent(), theDocument.getDocumentName());
			Doc.openPdf(theDocument.getDocumentName());
			
			SimplePackage myPackage = new SimplePackage();
			myPackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
			myPackage.setPackageName(documents.getOwnerEmailAddress());
			theModel.addAttribute("myPackage",myPackage);
		}
		 return "forward:/my-package";
	}

}