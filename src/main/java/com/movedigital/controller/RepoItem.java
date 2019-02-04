package com.movedigital.controller;

import com.movedigital.entities.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepoItem {

//    @Autowired
    private SessionFactory sessionFactory;

    public List<Item> findAllItem() {
        Session currentSession = sessionFactory.openSession();
        List<Item> all_items = currentSession.createQuery("from Item").list();
        currentSession.close();
        return all_items;
    }

    public Item addItem(Item item) {
        Session currentSession = sessionFactory.openSession();
//        Transaction transaction = currentSession.getTransaction();
//        TransactionStatus status = transaction.getStatus();

        currentSession.save(item);
        currentSession.flush();
        currentSession.close();
        return item;
    }

}
