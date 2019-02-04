package com.movedigital.controller;

import com.movedigital.entities.Contact;
import com.movedigital.impl.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class RepoController1 {

    @Autowired
    private Car voiture ;

//    @Autowired
    private SessionFactory sessionFactory;

    public void getCar() {
        voiture.display();
    }

    public Contact readContact(long idcont) {
        Session currentSession = sessionFactory.openSession();
//        Session currentSession = sessionFactory.getCurrentSession();
        Contact contact = currentSession.get(Contact.class, idcont);
        System.out.println("contact => " + contact);
        currentSession.close();
        return contact;
    }

    public List<Contact> findAllContact() {
        Session currentSession = sessionFactory.openSession();
        List contact = currentSession.createQuery("from Contact").list();
        currentSession.close();
        return contact;
    }

    public Contact addContact(Contact c1) {
//        Session currentSession1 = sessionFactory.getCurrentSession();
        Session currentSession = sessionFactory.openSession();

        Transaction transaction = currentSession.getTransaction();
        TransactionStatus status = transaction.getStatus();

        currentSession.save(c1);
        currentSession.flush();
        currentSession.close();
        return c1;
    }

}
