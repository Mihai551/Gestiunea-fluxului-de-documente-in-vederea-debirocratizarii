package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.config.Email;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailService extends Thread {

	private JavaMailSender emailSender = Email.getJavaMailSender();
	private String to;
	private String subject;
	private String text;

	public EmailService(String to, String subject, String text) {
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public void run() {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("gestiunea-fluxului-de-documente");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);

	}

}