package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackagesModel {
	
	public List<SimplePackage> list ;
	public SimplePackage thePackage;
	private String forUser;
	private String fromUser;
	private List<String> fromUsers;
	private String permission;
	private String packageName;

}
