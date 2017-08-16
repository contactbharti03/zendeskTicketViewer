package com.zendesk.ticketviewer.utility;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zendesk.ticketviewer.model.Ticket;
import com.zendesk.ticketviewer.service.TicketServices;

public class Utility {

	TicketServices ticketService = new TicketServices();
	private ObjectMapper objectMapper = new ObjectMapper();

	public String getAllTicketIds() throws JsonProcessingException, IOException {
		String ticketsString = ticketService.getAllTickets();
		return printTicketList(ticketsString);
	}

	public void getTicketDetails(int ticketNumber) {
		try{
		String response = ticketService.getTicket(ticketNumber + "");
		String ticketString = objectMapper.readTree(response).get("ticket").toString();
		Ticket ticket = objectMapper.readValue(ticketString, Ticket.class);
		printTicketDetails(ticket);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	private static void printTicketDetails(Ticket ticket) {
		System.out.println("Ticket ID: " + ticket.getId());
		System.out.println("Created at: " + ticket.getCreated_at());
		System.out.println("Updated at: " + ticket.getUpdated_at());
		System.out.println("Type: " + ticket.getType());
		System.out.println("Subject: " + ticket.getSubject());
		System.out.println("Description: " + ticket.getDescription());
		System.out.println("Priority: " + ticket.getPriority());
		System.out.println("Status: " + ticket.getStatus());
		
	}
	
	public String getMoreTicktes(String nextPage) throws JsonProcessingException, IOException{
		String ticketsString = ticketService.getMoreTickets(nextPage);
		return printTicketList(ticketsString);
	}
	
	private String printTicketList(String ticketsString) throws JsonProcessingException, IOException{
		JsonNode node = objectMapper.readTree(ticketsString);
		ArrayNode tickets = (ArrayNode) node.findPath("tickets");
		List<Ticket> ticketList = objectMapper.readValue(tickets.toString(), new TypeReference<List<Ticket>>() {
		});
		List<Integer> ticketIds = ticketList.stream().map(ticket -> ticket.getId()).collect(Collectors.toList());
		if(ticketIds.size()==0){
			System.out.println("No Tickets available. Enjoy your day.");
			System.exit(0);
		}
		System.out.println(ticketIds);
		String nextPage = objectMapper.readTree(ticketsString).get("next_page").asText();
		String next_page = null;
		if(nextPage.equalsIgnoreCase("null")){
			next_page=null;
		}else{
			next_page = nextPage.split("\\?")[1];
		}
		return next_page;
	}
	
}