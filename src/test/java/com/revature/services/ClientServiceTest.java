package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.AccountDTO;
import com.revature.models.Client;
import com.revature.models.ClientDTO;
import com.revature.repositories.ClientRepo;

class ClientServiceTest {
	
	private static Logger log = LoggerFactory.getLogger(ClientServiceTest.class);

	private static AccountDTO accountDTO;
	private static ClientDTO clientDTO;
	private static Client client;
	
	@InjectMocks
	ClientService clientService;
	
	@Mock
	private ClientRepo clientRepo;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setMockitoBeforeEachCall() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRegisterClient() {
		when(clientRepo.save(client)).then(null);
		//Need to complete this
	}

}
