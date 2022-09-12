package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AccountDTO;
import com.revature.models.Client;
import com.revature.models.ClientDTO;
import com.revature.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	private ClientService clientService;
	
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	@PostMapping("/register")
	@CrossOrigin
	public ResponseEntity<Object> addClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request){
		Client client = new Client();
		AccountDTO accountDTO = new AccountDTO();
		//creating DTO and appropriate object for the client
		
		client.setfName(clientDTO.getfName());
		client.setlName(clientDTO.getlName());
		client.setEmail(clientDTO.getEmail());
		//setting fields for the client to send to the Database;
		
		accountDTO.setUsername(clientDTO.getUsername());
		accountDTO.setPassword(clientDTO.getPassword());
		//setting fields in the accountDTO to be used later.
		log.info("Object entering the BackEnd is: " + client);
		
		try {
		Client newClient = clientService.registerClient(client, accountDTO);
		
			if (newClient!=null) {
				
				int clientId = newClient.getClientId();
				clientDTO.setId(clientId);
				clientDTO.setPassword(null);
				
				HttpSession session = request.getSession();
				session.setAttribute("role", "user");
				session.setAttribute("userAccount", client.getAccount());
				log.info("User has been successfully registered! Session created!");
				
				
				
				return ResponseEntity.status(HttpStatus.CREATED).body(clientDTO);
			} else {
				HttpSession session = request.getSession();
				session.invalidate();
				log.info("Something went wrong, session was not created since client was not created.");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				//Most likely don't need this piece. 
				}
		} catch(Exception e) {
			log.error("500 error, duplicate unqiue variable in database");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			
		}
	}
	
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Object> updateClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request){
		if(!clientService.clientExists(clientDTO)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		HttpSession session = request.getSession(false);
		if(session == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Client client = clientService.getClientbyID(clientDTO.getId());
		if(client.getAccount() != session.getAttribute("userAccount")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		clientService.updateClient(clientDTO);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

}
