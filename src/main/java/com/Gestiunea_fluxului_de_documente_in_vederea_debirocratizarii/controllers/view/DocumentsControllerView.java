package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.AccountDAO;

@Controller
public class DocumentsControllerView {

	@RequestMapping(value = "new-package-of-documents", method = RequestMethod.POST)
	public String newPackageOfDocuments(@ModelAttribute("SimpleUser") SimpleUser theUser,
			@ModelAttribute("package") DocumentPackage thePackage, ModelMap map) {

		DocumentPackage thePackage1 = new DocumentPackage();
		if (thePackage == null) {
			thePackage = new DocumentPackage();
			thePackage1.setPackageName("packageName");

		} else {
			thePackage1.setPackageName(thePackage.getPackageName());
		}

		map.addAttribute("SimpleUser", theUser);
		map.addAttribute("package", thePackage);
		map.addAttribute("package1", thePackage1);

		return "new-package";

	}
}
