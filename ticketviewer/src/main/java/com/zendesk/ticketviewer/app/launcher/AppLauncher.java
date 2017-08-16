package com.zendesk.ticketviewer.app.launcher;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zendesk.ticketviewer.utility.Utility;

public class AppLauncher {
	static Utility utility = new Utility();
	static String response ="";
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

			System.out.println("WELCOME TO ZENDESK TICKETVIEWER TASK");
			System.out.println("====================================");
			String exit = "";
			while(!exit.equalsIgnoreCase("exit")){
				System.out.println("Do you wish to see the list of tickets?(Y/N)");
				response = scanner.next();
				if(response.equalsIgnoreCase("Y")){
					String nextPage = utility.getAllTicketIds();
					while(nextPage!=null){
						System.out.println("You have more tickets");
						System.out.println("Do you want to see more Tickets?(Y/N)");
						response = scanner.next();
						if(response.equalsIgnoreCase("Y")){
							nextPage = utility.getMoreTicktes(nextPage);
						}else{
							nextPage = null;
						}
					}
					viewTickteDetails();
				}else if(response.equalsIgnoreCase("N")){
					viewTickteDetails();
				}else{
					System.out.println("SORRY!! INVALID RESPONSE");
				}
				System.out.println("If you wish to exit, then type exit");
				exit = scanner.next();
			}
			
	}
	
	public static void viewTickteDetails(){
		do{
			System.out.println("Do you wish to see the detials of ticket?(Y/N)");
			response = scanner.next();
			if(response.equalsIgnoreCase("Y")){
				System.out.println("Please provide the ticket number");
				String ticketNumber = scanner.next();
				try{
					int entry = Integer.parseInt(ticketNumber);
					utility.getTicketDetails(entry);
				}catch(NumberFormatException e){
					System.out.println("Thats a invalid entry!!");
				}
			}else if(!response.equalsIgnoreCase("N")){
				System.out.println("Thats a invalid entry!!");
			}
		}while(!response.equalsIgnoreCase("N"));
	}
}
