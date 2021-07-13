package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces.User;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Scope("prototype")
public class Individual implements User {
	

	private String type = "Individual";
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String firstName;
	

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String lastName;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String pin;

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String emailAddress;

	@NotNull(message="is required")
	@Size(min=6, message="Min length = 6")
	private String password;
	
	private List<String> myStreams;
	
	private List<String> streamsForMe;

	
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
