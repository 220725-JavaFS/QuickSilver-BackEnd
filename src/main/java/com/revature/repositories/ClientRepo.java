package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Client; //Make sure to import the client MODEL (Nigel)

public interface ClientRepo extends JpaRepository<Client, Integer>{

}
