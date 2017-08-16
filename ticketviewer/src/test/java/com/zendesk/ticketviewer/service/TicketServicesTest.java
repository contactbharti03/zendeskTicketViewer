package com.zendesk.ticketviewer.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zendesk.ticketviewer.Exceptions.ZendeskTicketException;
import com.zendesk.ticketviewer.service.TicketServices;

public class TicketServicesTest {
	TicketServices ticketServices = new TicketServices();
	
	@Test(expected = ZendeskTicketException.class)
	public void invalidTicket(){
		String response = ticketServices.getTicket("NA");
		assertEquals("",response);
	}
	
	@Test
	public void noMoreTickets(){
		String response = ticketServices.getMoreTickets("NA");
		ObjectMapper mapper = new ObjectMapper();
		try {
			int count = mapper.readTree(response).get("count").asInt();
			assertEquals(0,count);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
