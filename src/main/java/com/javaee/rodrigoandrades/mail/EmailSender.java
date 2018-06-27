package com.javaee.rodrigoandrades.mail;

public class EmailSender {
	public static void main(String[] args) {
		final String fromEmail = "rodrigomail2007@gmail.com";
		final String password = "******";
		final String toEmail = "rodrigomail2007@gmail.com";

		System.out.println("Initializing email send");

		EmailConfig config = new EmailConfig();

		config.sendEmail(fromEmail, password, toEmail, "Subject", "Email Body");
	}
}
