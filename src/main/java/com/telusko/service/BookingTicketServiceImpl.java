package com.telusko.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.telusko.request.Passenger;
import com.telusko.response.Ticket;

@Service
public class BookingTicketServiceImpl implements IBookingTicketService {

	private static final String BOOk_URL = "http://localhost:8088/TicketBookingApp/api/ticket-booking/getTicketNumber";
	private static final String GET_URL = "http://localhost:8088/TicketBookingApp/api/ticket-booking/getTicket/{ticketNumber}";
	

	@Override
	public Ticket bookTicket(Passenger passenger) 
	{
		System.out.println(passenger+" in Integration logic method");
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Ticket> response=restTemplate.postForEntity(BOOk_URL, passenger, Ticket.class);
//		
//		return response.getBody();
		
		//Get the  WebClient Object
		WebClient webClient = WebClient.create();
		
		//Send the request and process the data
		Ticket ticket = webClient
							.post()
							.uri(BOOk_URL)
							.bodyValue(passenger)
							.retrieve()
							.bodyToMono(Ticket.class)
							.block();//synchronous
		return ticket;
		
		
	}

	@Override
	public Ticket fetchTicketInfo(Integer ticketNumber) 
	{
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Ticket> response=restTemplate.getForEntity(GET_URL, Ticket.class,ticketNumber);
//		Ticket ticket=response.getBody();
//		return ticket;
		
		//Get the WebClient object
		WebClient webClient = WebClient.create();
		Ticket ticket=webClient
						.get()
						.uri(GET_URL,ticketNumber)
						.retrieve()
						.bodyToMono(Ticket.class)
						.block();
		
		return ticket;		
	}

}
