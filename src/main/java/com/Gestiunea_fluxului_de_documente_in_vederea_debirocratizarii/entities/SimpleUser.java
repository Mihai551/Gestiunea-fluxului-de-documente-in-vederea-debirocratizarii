package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SimpleUser {

	private String accountType;
	
	private String emailAddress;

	private String password;
	
	private String salt;
}
