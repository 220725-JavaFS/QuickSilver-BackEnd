package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{ //Unsure if I need the Integer Wrapper class

	Optional<Account> findByUsername(String username);
	
	//Creating method to find the whole account using the username entered (Nigel)
}
