package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Account;
import com.revature.models.AccountDTO;
import com.revature.models.Client;
import com.revature.models.Workouts;
import com.revature.repositories.ClientRepo;

@Service
public class ClientService {

	private ClientRepo clientRepo;
	private AccountService accountService;

	@Autowired
	public ClientService(ClientRepo clientRepo, AccountService accountService) {
		this.clientRepo = clientRepo;
		this.accountService = accountService;
	}

	public Client registerClient(Client client, AccountDTO accountDTO) {
		// beginning by getting the client and account objects here
		// turning the password into a int password to be sent to the database.
		int password = accountDTO.getPassword().hashCode();

		Account account = new Account();
		// Setting fields for account
		account.setUsername(accountDTO.getUsername());
		account.setUserPassword(password);
		// Now setting the account object INSIDE the client
		client.setAccount(account);

		Client dbClient = clientRepo.save(client);

		return dbClient;
	}

	public Client getClientByClientId(int id) {

		Optional<Client> opt = clientRepo.findById(id);

		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}

	}

	public List<Workouts> getWorkoutsByClientId(int id) {
		
		Optional<Client> opt = clientRepo.findById(id);

		if (opt.isPresent()) {
			return opt.get().getWorkouts();
		} else {
			return null;
		}

	}

}
