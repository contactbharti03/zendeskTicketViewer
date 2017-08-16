package com.zendesk.ticketviewer.service;

import java.io.IOException;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.zendesk.ticketviewer.Exceptions.ZendeskTicketException;
import com.zendesk.ticketviewer.property.reader.ApplicationFlowProperties;

public class TicketServices {

	private ApplicationFlowProperties applicationProperties = ApplicationFlowProperties.init();
	private final String baseUrl;

	public TicketServices() {
		this.baseUrl = applicationProperties.getBaseURL();
	}

	public String getAllTickets() {
		try {
			String response = getResponse("tickets.json");
			return response;
		} catch (Exception e) {
			throw new ZendeskTicketException(e);
		}
	}

	public String getTicket(String id) {
		try {
			String response = getResponse("tickets/" + id + ".json");
			return response;
		} catch (Exception e) {
			throw new ZendeskTicketException(e.getMessage());
		}
	}

	public String getMoreTickets(String nextPage) {
		try {
			String response = getResponse("tickets.json?" + nextPage);
			return response;
		} catch (Exception e) {
			throw new ZendeskTicketException(e);
		}
	}

	private String getResponse(String url) {
		try {
			HttpGet request = new HttpGet(baseUrl + url);
			String credential = Base64.getEncoder().encodeToString(
					(applicationProperties.getUserName() + ":" + applicationProperties.getPassword()).getBytes());
			request.setHeader("Authorization", "Basic " + credential.substring(0, credential.length() - 1));
			request.setHeader("Accept", "application/json");
			HttpResponse response = HttpClientBuilder.create().build().execute(request);

			if (isNotResponseOk(responseStatus(response)))
				throw new ZendeskTicketException(responseToString(response));

			return responseToString(response);

		} catch (Exception e) {
			throw new ZendeskTicketException(e);

		}
	}

	private int responseStatus(HttpResponse httpResponse) {
		return httpResponse.getStatusLine().getStatusCode();
	}

	private String responseToString(HttpResponse response) throws IOException {
		return EntityUtils.toString(response.getEntity());
	}

	private boolean isNotResponseOk(int responseStatus) {
		return !isResponseOk(responseStatus);
	}

	private boolean isResponseOk(int responseStatus) {
		return responseStatus == HttpStatus.SC_OK;
	}

}
