package com.revature.controllers;

import java.util.List;

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

import com.revature.models.Account;
import com.revature.models.AccountDTO;
import com.revature.services.AccountService;


@RestController //Making sure Spring can find this controller
@RequestMapping("/account") //Temporary mapping to check if this all works
@CrossOrigin
public class AccountController {
	
	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	private static String sessionId;
	
	private AccountService accountService;
	
	private static HttpSession session;
	
	
	public AccountController(AccountService accountService) { //More Constructor Injection
		super();
		this.accountService = accountService;
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAccounts(HttpServletRequest request){
		List<Account> accounts = accountService.getAllAccounts();
		HttpSession session = request.getSession(false);
		String sessions = session.getId();
		
		log.info(sessions);
		return ResponseEntity.status(200).body(accounts);
	} //This should return ALL accounts if they were made. 
	
	@PostMapping("/logout")
	@CrossOrigin
	public ResponseEntity<Object>LogoutUser(@RequestBody AccountDTO account, HttpServletRequest request){
		session = request.getSession(false);
		log.info("logging out user!");
		if(session!=null) {
			//HttpSession session = request.getSession();
			session.invalidate();
			log.info("Invalidating session");
			return ResponseEntity.status(HttpStatus.OK).body(account);
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();	
		}
	}
	
	@PostMapping("/check")
	@CrossOrigin
	public ResponseEntity<Object>CheckSession(@RequestBody AccountDTO account, HttpServletRequest request){
		session = request.getSession(false);
		log.info("Session is being checked!");
		
		if(session!=null) {
			log.info("Success! User session has been checked! Session Id is: "+session.getId()+". It was created on: "+session.getCreationTime());
			if(sessionId.equals(session.getId())) {
				String gettingUser = (String) session.getAttribute("username");
				
				AccountDTO accountResp = new AccountDTO();
				accountResp.setId(1);
				accountResp.setUsername(gettingUser);
				accountResp.setPassword("password");
				
				log.info("Session has been verified for the client");
				return ResponseEntity.status(HttpStatus.OK).body(accountResp);
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
		}else {
			log.info("No Session exists");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	
	
	@PostMapping("/login") //Just changed this to object.

	public ResponseEntity<Object> loginUser(@RequestBody AccountDTO account, HttpServletRequest request){
		String username = account.getUsername(); //Making sure to have HTTPServletRequest here to track session
		String password = account.getPassword();
		//Using the DTO to store the values of username and password and then passing them down
		
		log.info("Account info being sent to the Back End: " + account);
		
		Account trueAccount = accountService.getAccountByUsername(username, password);
		
		if (trueAccount!=null) {
		    session = request.getSession();
			System.out.println(session);
			session.setAttribute("role", "user");
			String storedUsername = trueAccount.getUsername();
			session.setAttribute("username", storedUsername);
			sessionId = session.getId();
			log.info("Success! User session has been created! Session Id is: "+session.getId()+". It was created on: "+session.getCreationTime());
			
			int id = trueAccount.getAccountId();
			account.setId(id);
			account.setPassword(null);
		
			log.info("Account info being sent back to the Front End: " + account);
			
			return ResponseEntity.status(HttpStatus.OK).body(account);
			//Sending back an OK response
			//.header("Access-Control-Allow-Origin", "http://localhost").header("Access-Control-Allow-Credentials", "true").
		}else {
			session = request.getSession();
			session.invalidate();
			log.info("User information was incorrect, session destroyed just in case.");
			return ResponseEntity.status(204).build();
		}
		
		//When using any other Method, it is BEST use the request object and do request.getSession(false) to check if there is already a session. 
	}
	
}
