package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Component
@Scope("prototype")
public class LegalEntity implements User {
	
	
	@Setter
	@Getter
	private String type = "LegalEntity";	

	@NotNull(message="is required")
	@Size(min=1, message="is required")	
	@Setter
	@Getter
	private String legalEntityName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String emailAddress;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String country;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String city;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String address;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Setter
	@Getter
	private String password;
	
	@Setter
	@Getter
	private List<String> inviteCodes;
	
	@Setter
	@Getter
	private List<String> myStreams;
	
	@Setter
	@Getter
	private List<String> streamsForMe;

	
	
	@Override
	public void addInviteCode(String inviteCode) {
		
		this.inviteCodes.add(inviteCode);
	}
	@Override
	public void removeInviteCode(String inviteCode) {
		
		this.inviteCodes.remove(new String(inviteCode));
	}
	
}
