package com.developers.service.impls;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.developers.service.iface.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService{

	private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender sender;

	@Override
	public boolean sendEmail(String message, String to, String subject) {
		log.info("Mensaje {}", message);
		return sendEmailTool(message, to, subject);
	}
	
	private boolean sendEmailTool(String message, String to, String subject) {
		boolean sent = false;
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setFrom("noreply@iudigital.edu.co");
			helper.setTo(to);
			helper.setText(message, true);
			helper.setSubject(subject);
			sender.send(msg);
			sent = true;
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("Error: {}", e);
		}
		
		return sent;
	}
}
