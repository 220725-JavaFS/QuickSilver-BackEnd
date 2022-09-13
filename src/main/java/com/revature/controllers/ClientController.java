package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<Object> getClientById(@PathVariable("id") int accountId){
		
		return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientByClientId(accountId));
	}
	
	
	

}
