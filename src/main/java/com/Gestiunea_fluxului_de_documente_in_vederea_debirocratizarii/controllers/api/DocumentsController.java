package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.api;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;

@Controller
public class DocumentsController {

	@RequestMapping("/processForm-new-package")
	public String addPackage(@ModelAttribute("SimpleUser") SimpleUser theUser,
			@ModelAttribute("package") DocumentPackage thePackage, Model theModel, BindingResult result)
			throws IOException {

		theModel.addAttribute("SimpleUser", theUser);

		byte[] encoded = Doc.pdfToBlob(thePackage.getIN_FILE());
		thePackage.setDocumentContent(encoded);

		DocumentsDAO.addDocument(thePackage);

		return "forward:/new-package-of-documents";

	}

}