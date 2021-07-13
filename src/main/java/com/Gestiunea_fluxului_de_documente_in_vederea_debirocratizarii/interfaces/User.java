package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.interfaces;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public interface User {

	void addInviteCode(String inviteCode);

	void removeInviteCode(String inviteCode);

}
