package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;
import com.revature.models.AccountDTO;
import com.revature.services.AccountService;


@RestController //Making sure Spring can find this controller
@RequestMapping("/account") //Temporary mapping to check if this all works
public class AccountController {
	
	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	private AccountService accountService;
	
	public AccountController(AccountService accountService) { //More Constructor Injection
		super();
		this.accountService = accountService;
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAccounts(){
		List<Account> accounts = accountService.getAllAccounts();
		return ResponseEntity.status(200).body(accounts);
	} //This should return ALL accounts if they were made. 
	
	
	
	@GetMapping("/login") 
	public ResponseEntity<Account> loginUser(@RequestBody AccountDTO account, HttpServletRequest request){
		String username = account.getUsername(); //Making sure to have HTTPServletRequest here to track session
		String password = account.getPassword();
		//Using the DTO to store the values of username and password and then passing them down
		
		Account trueAccount = accountService.getAccountByUsername(username, password);
		if (trueAccount!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("role", "user");
			log.info("Success! User session has been created!");
			
			return ResponseEntity.status(HttpStatus.OK).build();
			//Sending back an OK response
		}else {
			HttpSession session = request.getSession();
			session.invalidate();
			log.info("User information was incorrect, session destroyed just in case.");
			return ResponseEntity.status(204).build();
		}
		
		//When using any other Method, it is BEST use the request object and do request.getSession(false) to check if there is already a session. 
	}
	

}
