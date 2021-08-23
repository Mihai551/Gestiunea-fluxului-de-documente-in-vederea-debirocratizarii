package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePackage {

	private String ownerEmailAddress;
	private String packageDescription;
	private String packageName;
	private String signingFlowEnable;
	
	private List<String> permissions;
	private String permissionEmailAddress;
	

}
