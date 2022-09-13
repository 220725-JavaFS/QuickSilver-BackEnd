package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Account;
import com.revature.models.Client;

public interface ClientRepo extends JpaRepository<Client, Integer>{
	List<Client> findAllByAccount(Account account);

}
