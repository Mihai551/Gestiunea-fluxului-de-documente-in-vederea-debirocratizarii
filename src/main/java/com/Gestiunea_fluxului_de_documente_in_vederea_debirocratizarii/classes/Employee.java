package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;

@Component
@Scope("prototype")
public class Employee implements User {
	
	@Autowired
	private String type = "Employee";
	
	private String legalEntityName;
	
	private String firstName;
	
	private String lastName;
	
	private String pin;
	
	private String emailAdress;
	
	private String password;
	
	private List<String> myStreams;
	
	private List<String> streamsForMe;
	
	private String inviteCode;

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getType() {
		return type;
	}

	@Override
	public void addInviteCode(String inviteCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeInviteCode(String inviteCode) {
		// TODO Auto-generated method stub
		
	}

}
