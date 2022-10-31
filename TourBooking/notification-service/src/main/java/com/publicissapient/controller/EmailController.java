package com.publicissapient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.entity.EmailMessage;
import com.publicissapient.service.EmailSendService;
import com.publicissapient.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	EmailSendService emailSenderService;
	
    @PostMapping(path="/send-email",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    @Timed(value="request.email.send-email")
    public ResponseEntity<Message> sendEmail(@RequestBody EmailMessage emailMessage) {
        this.emailSenderService.sendSimpleEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok(new Message("Email Sent"));
    }
}
