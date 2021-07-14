package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope("prototype")
public class Employee implements User {
	
	
	@Setter
	@Getter
	private String type = "Employee";

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String legalEntityName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")	
	@Setter
	@Getter
	private String firstName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String lastName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String pin;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String emailAddress;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String password;
	
	@Setter
	@Getter
	private List<String> myStreams;
	
	@Setter
	@Getter
	private List<String> streamsForMe;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String inviteCode;


	@Override
	public void addInviteCode(String inviteCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeInviteCode(String inviteCode) {
		// TODO Auto-generated method stub
		
	}

}
