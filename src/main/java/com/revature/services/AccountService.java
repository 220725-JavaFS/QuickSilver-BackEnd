package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Account;
import com.revature.repositories.AccountRepo;

@Service //Declaring this class as a service (Nigel)
public class AccountService {
	
	private AccountRepo accountRepo; //Creating variable of AccountRepo
	
	//I am also mocking this constructor call in the test class using @InjectMockito
     @Autowired 
	public AccountService(AccountRepo accountRepo) { //Constructor Injection
		this.accountRepo = accountRepo;
	}
	
	//Going to make a few methods (May not need most of them)
	
	public List<Account> getAllAccounts(){
		return accountRepo.findAll();
	}
	
	public Account getAccountById (int id) {
		Optional<Account> opt = accountRepo.findById(id);
		//Checking is the information is null already.
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}
	
	public Account getAccountByUsername (String username, String passwordString) {
		int userPassword = passwordString.hashCode();
		//Attempting to hashcode the password String the user passess to the database
			try {
				Optional<Account> opt = accountRepo.findByUsername(username);
				
				//opt is a Account object which means I can access the fields of the account
				if(opt.isPresent()) {
					
					if(userPassword!=opt.get().getUserPassword()) { //Method to check if the password "hashcoded" equals the value in the database with the username
						return null;
					} else {
						return opt.get();
					}
					
				} else {
					return null;
				}
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			//Surrounded the entire code block in a try catch if an exception is thrown
	}
	//If this method works right, it will ONLY return the object if the password they entered equals the one in the database (hashcoded of course)

}
