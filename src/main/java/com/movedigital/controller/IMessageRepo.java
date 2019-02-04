package com.movedigital.controller;

import com.movedigital.entities.Contact;
import com.movedigital.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IMessageRepo extends JpaRepository<Message, Long> {

    @Query("SELECT u FROM Message u WHERE u.contact = :#{#contact}")
    Collection<Message> findAllActiveUsers(@Param("contact") Contact contact);
//    @Query("SELECT u FROM Message u WHERE u.contact = ?#{[0]}")
//    Collection<Message> findAllActiveUsers(long idContact);

}
