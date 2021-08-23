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

import ch.qos.logback.core.net.SyslogOutputStream;

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
		DocumentsModel documents = new DocumentsModel();
		documents = DocumentsDAO.pullDocuments(thePackage);
		thePackage.setSigningFlowEnable(DocumentsDAO.setSigningFlowEnableFromDB(thePackage));
		String enable_disable = "Enable signing flow option";

		try {
			if (thePackage.getSigningFlowEnable().equalsIgnoreCase("1")) {
				enable_disable = "Disable signing flow option";
			}
		} catch (Exception e) {

		}

		theModel.addAttribute("documents", documents);
		theModel.addAttribute("myPackage", thePackage);
		theModel.addAttribute("enable_disable", enable_disable);
		
		return "myPackage-documents";
	}

	@RequestMapping(value = "select-owner", method = RequestMethod.POST)
	public String selectOwner(@ModelAttribute("SimpleUser") SimpleUser theUser, Model theModel) {

		PackagesModel packages = new PackagesModel();
		packages.setFromUsers(DocumentsDAO.pullOwnersList(theUser));
		packages.setForUser(theUser.getEmailAddress());

		theModel.addAttribute("packages", packages);

		return "selectOwner";
	}

	@RequestMapping(value = "select-package", method = RequestMethod.POST)
	public String selectPackage(@ModelAttribute("packages") PackagesModel packages, Model theModel) {
		System.out.println("select-package " + packages.getForUser());
		packages.list = DocumentsDAO.pullPackagesOfOthers(packages);
		SimplePackage thePackage = new SimplePackage();
		thePackage.setPermissionEmailAddress(packages.getForUser());
		theModel.addAttribute("packages", packages);
		theModel.addAttribute("thePackage", thePackage);

		return "selectPackage";
	}

	@RequestMapping(value = "package-for-me", method = RequestMethod.POST)
	public String packageForMe(@ModelAttribute("thePackage") SimplePackage thePackage, Model theModel) {
		
		System.out.println(thePackage.getOwnerEmailAddress());
		DocumentsModel documents = new DocumentsModel();
		documents = DocumentsDAO.pullDocuments(thePackage);

		theModel.addAttribute("documents", documents);
		theModel.addAttribute("thePackage", thePackage);
		
		return "package-for-me-documents";

	}

	@RequestMapping(value = "define-signing-flow", method = RequestMethod.POST)
	public String defineSigningFlow(@ModelAttribute("myPackage") SimplePackage thePackage, Model theModel) {
		List<String> users = DocumentsDAO.pullPermissionsOfPackage(thePackage);
		
		for (String user : users) {
			System.out.println(user);
		}
		SigningFlow theSigningFlow = new SigningFlow();
		theModel.addAttribute("myPackage", thePackage);
		System.out.println("define-signing-flow   " + thePackage.getOwnerEmailAddress());
		System.out.println("define-signing-flow   " + thePackage.getPackageName());
		theModel.addAttribute("users", users);
		theModel.addAttribute("signingFlow", theSigningFlow);

		return "define-signing-flow";

	}

}
