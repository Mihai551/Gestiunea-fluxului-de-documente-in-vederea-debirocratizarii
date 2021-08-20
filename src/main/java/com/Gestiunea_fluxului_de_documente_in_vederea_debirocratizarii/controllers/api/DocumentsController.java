package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.DocumentServices;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.SignatureServices;

@Controller
public class DocumentsController {

	@RequestMapping("/processForm-new-package")
	public String addPackage(@ModelAttribute("SimpleUser") SimpleUser theUser,
			@ModelAttribute("package") DocumentPackage thePackage, Model theModel, BindingResult result)
			throws IOException {

		theModel.addAttribute("SimpleUser", theUser);

		// add simple package
		DocumentServices.addPackage(thePackage);
		thePackage.setPermissionEmailAddress(thePackage.getOwnerEmailAddress());
		thePackage.setPermission("Sign");
		DocumentServices.addPermission(thePackage);
		return "forward:/new-package-of-documents";

	}

	@RequestMapping("/my-document")
	public String viewDoc(HttpServletRequest request, @ModelAttribute("documents") DocumentsModel documents,
			Model theModel) throws Exception {

		try {

			if (documents.getAction().equalsIgnoreCase("View")) {
				request.setAttribute("documents", documents);
				return "forward:/getPDF";

			}

			if (documents.getAction().equalsIgnoreCase("Sign")) {
				if (DocumentServices.Sign(documents, "owner")) {
					SignatureServices.digitalSign(documents.getOwnerEmailAddress(), documents.getPackageName(),
							documents.getDocumentName(), documents.getOwnerEmailAddress());
				}

			}
			SimplePackage myPackage = new SimplePackage();
			myPackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
			myPackage.setPackageName(documents.getOwnerEmailAddress());
			theModel.addAttribute("myPackage", myPackage);

			if (documents.getAction().equalsIgnoreCase("Signatures")) {

				// de regandit

				List<String> signatures = SignatureServices.digitalSignatureValidation(documents);
				theModel.addAttribute("signatures", signatures);
			}

		} catch (Exception e) {

		}

		return "forward:/my-package";
	}

	@RequestMapping("/document")
	public String viewDocWithPermission(@ModelAttribute("documents") DocumentsModel documents, Model theModel,
			HttpServletResponse response) throws Exception {

		documents.setPermissions(DocumentsDAO.checkPermissions(documents));

		for (String x : documents.getPermissions()) {
			System.out.println("/document " + x);
		}
		try {

			if (documents.getAction().equalsIgnoreCase("View")) {

				return "forward:/getPDF";
			}

			if (documents.getAction().equalsIgnoreCase("Sign") && documents.getPermissions().contains("Sign")) {
				if (DocumentServices.Sign(documents, "notOwner")) {
					SignatureServices.digitalSign(documents.getOwnerEmailAddress(), documents.getPackageName(),
							documents.getDocumentName(), documents.getPermissionEmailAddress());

				}
			}
			SimplePackage myPackage = new SimplePackage();
			myPackage.setOwnerEmailAddress(documents.getOwnerEmailAddress());
			myPackage.setPackageName(documents.getOwnerEmailAddress());
			theModel.addAttribute("myPackage", myPackage);

			if (documents.getAction().equalsIgnoreCase("Signatures")) {

				// de regandit

				List<String> signatures = SignatureServices.digitalSignatureValidation(documents);
				theModel.addAttribute("signatures", signatures);
			}

		} catch (Exception e) {

		}

		return "forward:/package-for-me";
	}

	@PostMapping("/add-permission")
	public String addPermission(HttpServletRequest request) {
		DocumentPackage thePackage = new DocumentPackage();

		thePackage.setOwnerEmailAddress(request.getParameter("ownerEmailAddress"));
		thePackage.setPackageName(request.getParameter("packageName"));
		thePackage.setPermission(request.getParameter("permission"));
		thePackage.setPermissionEmailAddress(request.getParameter("permissionEmailAddress"));

		// add permission
		DocumentServices.addPermission(thePackage);

		return "forward:/my-package";

	}

	@PostMapping("/add-step")
	public String addStep(@ModelAttribute("signingFlow") SigningFlow signingFlow, Model theModel) {
		SimplePackage thePackage = new SimplePackage();
		thePackage.setOwnerEmailAddress(signingFlow.getOwnerEmailAddress());
		System.out.println(signingFlow.getOwnerEmailAddress());
		thePackage.setPackageName(signingFlow.getPackageName());
		System.out.println(signingFlow.getPackageName());

		System.out.println("add step   " + thePackage.getOwnerEmailAddress() + "   " + thePackage.getPackageName());
		theModel.addAttribute("myPackage", thePackage);

		DocumentsDAO.addStep(signingFlow);
		return "forward:/define-signing-flow";

	}

	@PostMapping("enable-disalbe-signingFlow")
	public String enableDisableSigningFlow(@ModelAttribute("myPackage") SimplePackage thePackage, Model theModel) {

		DocumentServices.enableOrDisable(thePackage);
		theModel.addAttribute("myPackage", thePackage);

		return "forward:/my-package";

	}
}