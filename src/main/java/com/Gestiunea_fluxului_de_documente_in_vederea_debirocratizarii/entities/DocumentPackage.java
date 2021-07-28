package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentPackage {
	
	private String ownerEmailAddress;

	private String packageName;
	
	private String documentName;
	
	private byte[] documentContent;
	
	private String description;

	private String permissionView;
	
	private String permissionSign;
	
	private String IN_FILE; 
	

}
