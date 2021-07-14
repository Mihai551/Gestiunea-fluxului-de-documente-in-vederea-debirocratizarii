package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
//@Scope("prototype")
public class Individual implements User {

	@Getter
	private String type = "Individual";

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String lastName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String pin;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Setter
	@Getter
	private String emailAddress;

	@NotNull(message = "is required")
	@Size(min = 6, message = "Min length = 6")
	@Setter
	@Getter
	private String password;

	@Setter
	@Getter
	private List<String> myStreams;

	@Setter
	@Getter
	private List<String> streamsForMe;

	@Override
	public void addInviteCode(String inviteCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeInviteCode(String inviteCode) {
		// TODO Auto-generated method stub

	}

}
