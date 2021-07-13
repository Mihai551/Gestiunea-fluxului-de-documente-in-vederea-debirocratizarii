package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Component
@Scope("prototype")
public class LegalEntity implements User {
	
	
	private String type = "LegalEntity";	

	@NotNull(message="is required")
	@Size(min=1, message="is required")	
	private String legalEntityName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String emailAddress;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String country;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String city;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String address;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String password;
	
	private List<String> inviteCodes;
	
	private List<String> myStreams;
	
	private List<String> streamsForMe;

	

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public List<String> getInviteCodes() {
		return inviteCodes;
	}

	public List<String> getMyStreams() {
		return myStreams;
	}

	public void setMyStreams(List<String> myStreams) {
		this.myStreams = myStreams;
	}

	public List<String> getStreamsForMe() {
		return streamsForMe;
	}

	public void setStreamsForMe(List<String> streamsForMe) {
		this.streamsForMe = streamsForMe;
	}
	
	@Override
	public void addInviteCode(String inviteCode) {
		
		this.inviteCodes.add(inviteCode);
	}
	@Override
	public void removeInviteCode(String inviteCode) {
		
		this.inviteCodes.remove(new String(inviteCode));
	}
	
}
