package com.movedigital.controller;

import com.movedigital.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.Repository;

@Repository
public interface IContactRepo extends JpaRepository<Contact, Long> {



}
