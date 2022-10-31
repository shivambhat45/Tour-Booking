package com.publicissapient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendServiceImpl implements EmailSendService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendSimpleEmail(String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("shivambhat45@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		this.mailSender.send(message);
	}

}
