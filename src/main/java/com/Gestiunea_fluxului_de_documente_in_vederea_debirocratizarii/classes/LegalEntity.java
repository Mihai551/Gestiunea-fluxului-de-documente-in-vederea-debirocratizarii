package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;
import java.util.List;
@Component
@Scope("prototype")
public class LegalEntity implements User {
	
	@Autowired
	private String type = "LegalEntity";	
	
	private String legalEntityName;
	
	private String emailAddress;
	
	private String country;
	
	private String City;
	
	private String address;
	
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
		return City;
	}

	public void setCity(String city) {
		City = city;
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
