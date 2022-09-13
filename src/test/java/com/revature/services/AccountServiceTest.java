package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.controllers.AccountController;
import com.revature.models.Account;
import com.revature.repositories.AccountRepo;

class AccountServiceTest {
	
	private static String username;
	private static String passwordString;
	private static int hashcodedPassword;
	
	@InjectMocks
	AccountService accountService;
    //Use as the AccountService
	
	@Mock
    private AccountRepo accountRepo;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setMockitorBeforeEachCall() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void getAccountByUsernameTestIfNull() {
		//Testing if the value put in fails with the values put in
	 when(accountRepo.findByUsername(username)).thenReturn(Optional.of((new Account(1, "test", 384539539))));
	 //(Stream.of(new Optional<Account>(1,"test", 3556498)));
		username = "test";
		passwordString = "test";
		//mocking a database call
		assertEquals(null, accountService.getAccountByUsername(username, passwordString));
	}
	
	@Test
	void getAccountByUsernameTestIfNotNull() {
		username = "test";
		passwordString = "test";
		//mocking a database call
	   when(accountRepo.findByUsername(username)).thenReturn(Optional.of((new Account(1, "test", 3556498))));
		assertNotEquals(null, accountService.getAccountByUsername(username, passwordString));
	}
	
	@Test
	void getAccountsTest() {
		when(accountRepo.findAll()).thenReturn(Stream.of(new Account(1,"test", 3556498), new Account (3, "test2", 349202)).collect(Collectors.toList()));
		assertEquals(2, accountService.getAllAccounts().size());
	}
	

}
