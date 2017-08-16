package com.zendesk.ticketviewer.Exceptions;

public class ZendeskTicketException extends RuntimeException {

	private static final long serialVersionUID = -8069782567783450118L;

	public ZendeskTicketException(Exception e) {
		super(e);
	}

	public ZendeskTicketException(String message) {
		super(message);
	}


}