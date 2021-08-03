package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.*;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.AccountDAO;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;

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

	@RequestMapping(value = "my-packages", method = RequestMethod.POST)
	public String myPackages(@ModelAttribute("SimpleUser") SimpleUser theUser, Model theModel) {

		PackagesModel packages = new PackagesModel();
		packages = DocumentsDAO.pullPackages(theUser);
		SimplePackage thePackage = new SimplePackage();
		thePackage.setOwnerEmailAddress(packages.list.get(0).getOwnerEmailAddress());

		theModel.addAttribute("packages", packages);
		theModel.addAttribute("myPackage", thePackage);

		return "myPackages";
	}

	@RequestMapping(value = "my-package", method = RequestMethod.POST)
	public String myPackage(@ModelAttribute("myPackage") SimplePackage thePackage, Model theModel) {
		System.out.println(thePackage.getPackageName()); // -- NullPointer
		System.out.println(thePackage.getOwnerEmailAddress());
		DocumentsModel documents = new DocumentsModel();
		documents = DocumentsDAO.pullDocuments(thePackage);

		theModel.addAttribute("documents", documents);
		theModel.addAttribute("myPackage", thePackage);

		return "myPackage-documents";
	}
}
