package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;
import com.aspose.words.Document;

public class OtherServices {
	
	public static boolean containsPackageName(final List<SimplePackage> list, final String name){
	    return list.stream().filter(o -> o.getPackageName().equals(name)).findFirst().isPresent();
	}
	
	public static org.w3c.dom.Document newDocumentFromInputStream(InputStream in) {
	    DocumentBuilderFactory factory = null;
	    DocumentBuilder builder = null;
	    org.w3c.dom.Document ret = null;

	    try {
	      factory = DocumentBuilderFactory.newInstance();
	      builder = factory.newDocumentBuilder();
	    } catch (ParserConfigurationException e) {
	      e.printStackTrace();
	    }

	    try {
	      ret = builder.parse(new InputSource(in));
	    } catch (SAXException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return ret;
	  }

	}


