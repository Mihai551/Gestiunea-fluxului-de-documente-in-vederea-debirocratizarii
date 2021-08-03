package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentsModel {

	private String action;
	public SimplePackage thePackage = new SimplePackage();
	private String packageName;
	private String ownerEmailAddress;

	public List<Doc> list;
	private String documentName;

}
