package com.publicissapient.service;

public interface EmailSendService {

	void sendSimpleEmail(String toEmail,String body,String subject);
}
