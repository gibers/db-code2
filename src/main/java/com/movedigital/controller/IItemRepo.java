package com.movedigital.controller;

import com.movedigital.entities.Contact;
import com.movedigital.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//import org.springframework.data.repository.Repository;

@Repository
public interface IItemRepo extends JpaRepository<Item, Long> {



    List<Item> findByName(String name);

//    Contact findByContact_idcont(long idcont);

//    long countByContact_idcont(long idcont);

//    List<Item> findByContact_idcont(long idcont);

}
