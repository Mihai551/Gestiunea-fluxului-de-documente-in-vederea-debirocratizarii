package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.controllers.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.Doc;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.persistence.DocumentsDAO;
import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services.DocumentServices;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String documentName = request.getParameter("documentName"); // Retrieves <input type="text" name="description">
		javax.servlet.http.Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		String ownerEmailAddress = request.getParameter("ownerEmailAddress");
		System.out.println(ownerEmailAddress);
		String packageName = request.getParameter("packageName");

		try {
			DocumentPackage thePackage = new DocumentPackage();
			thePackage.setDocumentName(documentName);
			thePackage.setPackageName(packageName);
			thePackage.setOwnerEmailAddress(ownerEmailAddress);
			if (DocumentServices.addDocument(thePackage)) {
				Doc document = DocumentsDAO.pullDocument(thePackage);
				InputStream fileContent = (filePart.getInputStream());

				String serverPath = "C:\\Users\\Mihai\\Desktop\\Documents\\" + document.getId() + ".pdf";

				FileOutputStream pdf = new FileOutputStream(serverPath);

				byte[] buf = new byte[8192];
				int length;
				while ((length = fileContent.read(buf)) > 0) {
					pdf.write(buf, 0, length);
				}

				pdf.flush();
				pdf.close();
			}
		} catch (Exception e) {
		}

	}
}