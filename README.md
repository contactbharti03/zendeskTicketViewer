		--------------------------------------------------------------------						
								Zendesk Ticket Viewer 
		--------------------------------------------------------------------	

Zendesk task to list and view the ticket details.

Prerequisites
	Java (SE 1.8)
	Maven 3.3.1

How to use the application?
	- First, open command prompt/terminal.
	- Go to the project root directory.
	- Run following command
		mvn clean install
	- Then run the following command
		mvn exec:java  -Dexec.mainClass="com.zendesk.ticketviewer.app.launcher.AppLauncher"
	Now the program ask for user inputs.
	
	user must select input type.
	
	WELCOME TO ZENDESK TICKETVIEWER TASK
	====================================
	Do you wish to see the list of tickets?(Y/N)
	
	If user enter Y, then 
	[1,2,3,4,5,6,7,8,9...]
	
	If user enter N, then
	Do you wish to see the detials of ticket?(Y/N)
	
	If user enter Y, then 
	Please provide the ticket number
	
	If user enter <VALID TICKET NUMBER>, then
	Ticket Id: 1
	Description: description....
	
	If user enter <INVALID TICKET NUMBER>, then
	com.zendesk.ticketviewer.Exceptions.ZendeskTicketException: {"error":"RecordNotFound","description":"Not found"}
	
	If user enter <ALPHABET>, then
	SORRY!! INVALID RESPONSE
	If you wish to exit, then type exit
	
	If user enter no, then
	same set of question
	
	If user enter exit, then
	program ends