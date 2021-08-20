package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SigningFlow {

	private String ownerEmailAddress;

	private String packageName;

	private String user;

	private String step;

}
