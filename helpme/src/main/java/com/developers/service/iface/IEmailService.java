package com.developers.service.iface;

public interface IEmailService {
	
	public boolean sendEmail(String message, String to, String subject);
}
