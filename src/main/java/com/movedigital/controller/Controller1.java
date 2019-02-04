package com.movedigital.controller;

import com.movedigital.entities.Commandes;
import com.movedigital.entities.Contact;
import com.movedigital.entities.Item;
import com.movedigital.entities.Message;
import com.movedigital.impl.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/")
public class Controller1 {

    @Autowired
    private Car car;

    @Autowired
    private RepoController1 repoC1;

    @Autowired
    private RepoItem repoItem;

    @Autowired
    private IContactRepo iContactRepo;

    @Autowired
    private IItemRepo iitemRepo;

    @Autowired
    private IMessageRepo iMessageRepo;


//    public Controller1(IContactRepo iContactRepo) {
//        this.iContactRepo = iContactRepo;
//    }

    @RequestMapping(value="affJson", method = RequestMethod.GET, produces = "application/json")
    public List<String> affJson() {
        List<String> listArmes = new ArrayList<>();
        listArmes = Arrays.asList("object1", "object2", "object3");
        return listArmes;
    }

    @RequestMapping(value= "aff/{id}", method = RequestMethod.GET)
    public String affUnique(@PathVariable("id") long idcont, Map<String, Object> model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        String id = session.getId();
        Boolean isConnected = (Boolean) session.getAttribute("isConnected");

//        repoC1.getCar();
        System.out.println("bonjour les petites gens avec gillet jaunes id : " + id);
        System.out.println("isConnected : " + isConnected );

        Contact contact = repoC1.readContact(idcont);
        model.put("contacts", contact);
        return "home";
    }

    @RequestMapping(value= "aff", method = RequestMethod.GET)
    public String aff(Map<String, Object> model) {
        repoC1.getCar();
        System.out.println("bonjour les petites gens");
        List<Contact> contact = repoC1.findAllContact();
        model.put("contacts", contact);
        return "home";
    }



    @RequestMapping(value="affItem", method= RequestMethod.GET)
    public String affItem(Map<String, Object> model) {
        List<Item> allItem = repoItem.findAllItem();
        model.put("item", allItem);
        System.out.println("nb item => " + allItem.size());
        allItem.forEach(x -> {
                System.out.println("item => " + x);
        });
        return "item";
    }

    @RequestMapping(value="addItem", method= RequestMethod.GET)
    public String addItem(Map<String, Object> model) {

        Optional<Contact> byId = iContactRepo.findById(2L);

//        Contact byContact = iitemRepo.findByContact_idcont(2L);

//        long l = iitemRepo.countByContact_idcont(2L);

        Item item = new Item();
        item.setName("skies");
        item.setContact_idcont(2L);
        item.setItemlist_order(byId.get().getItemList().size());
        iitemRepo.save(item);
        model.put("item", item );
        return "home";
    }

    @RequestMapping(value="addContact", method= RequestMethod.GET)
    public String addContact(Map<String, Object> model) {
        Contact c1 = new Contact();
        c1.setEmailaddress("emailNew@gmail.com");
        c1.setFirstname("Jean-Jacques");
        c1.setLastname("Ouerghim");
        c1.setPhonenumber("066666666666");
        repoC1.addContact(c1);
        model.put("contacts", c1);
        return "home";
    }

    // ---------------------------  with JPA repository.

    @RequestMapping(value="addCont", method= RequestMethod.GET)
    public String addCont(Map<String, Object> model) {
        Contact c1 = new Contact();
        c1.setEmailaddress("cochonOuerghim@gmail.com");
        c1.setFirstname("Cochon");
        c1.setLastname("Ouerghim");
        c1.setPhonenumber("055555555555");
        Contact save = iContactRepo.save(c1);
//        repoC1.addContact(c1);
        model.put("contacts", c1);
        return "home";
    }

    @RequestMapping(value="seeVal", method= RequestMethod.GET)
    public String seeVal(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(2L);
        Contact contact = byId.get();
        Set<String> nameItem = contact.getNameItem();
        afficheS(nameItem);
//        nameItem.forEach(x -> {
//            System.out.print("pour user idcont : " + contact.getIdcont() );
//            System.out.println(" item : " + x);
//        });
        return "home";
    }

    @RequestMapping(value = "seeValms", method= RequestMethod.GET)
    public String seeValms(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(2L);
        Contact contact = byId.get();
        Collection<String> nameItem = contact.getNamesItem();
        afficheS(nameItem);
//        nameItem.forEach(x -> {
//            System.out.print("pour user idcont : " + contact.getIdcont() );
//            System.out.println(" item : " + x);
//        });
        return "home";
    }

    @RequestMapping(value="seeValm", method= RequestMethod.GET)
    public String seeValm(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(2L);
        Contact contact = byId.get();
        List<String> nameItem = contact.getItemList();
        afficheS(nameItem);
//        nameItem.forEach(x -> {
//            System.out.print("pour user idcont : " + contact.getIdcont() );
//            System.out.println(" item : " + x);
//        });
        // suppression du 2ième élément :
//        nameItem.remove(1);
        iContactRepo.flush();
        return "home";
    }

    private void afficheS(Collection<String> commandes) {
        commandes.forEach(x -> {
            System.out.println("x.toString() => " + x);
        });
    }

    private void affiche(Collection<Commandes> commandes) {
        commandes.forEach(x -> {
            System.out.println("x.toString() => " + x);
        });
    }

    @RequestMapping(value="seeValEmb", method= RequestMethod.GET)
    public String seeValEmb(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(2L);
        Contact contact = byId.get();
        Set<Commandes> nameItem = contact.getCommandes();
        affiche(nameItem);
//        nameItem.forEach(x -> {
//            System.out.println("x.toString() => " + x);
//        });
        return "home";
    }

    @RequestMapping(value="seeValEmbm", method= RequestMethod.GET)
    public String seeValEmbm(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(2L);
        Contact contact = byId.get();
        Collection<Commandes> nameItem = contact.getCommandesMultiple();
        affiche(nameItem);
//        nameItem.forEach(x -> {
//            System.out.println("x.toString() => " + x);
//            x.setPoid(x.getPoid()+1);
//        });

        Commandes comm = new Commandes();
        comm.setPoid(26);
        comm.setHeight(96);
        comm.setIdCommande(3);
//        comm.setContact(contact);
        nameItem.add(comm);

//        contact.setCommandesMultiple(nameItem);
//        iContactRepo.save(contact);
        iContactRepo.flush();
        return "home";
    }

//    -------------------------- Messages -----------------------------

    @RequestMapping(value="seeMessageRequest", method= RequestMethod.GET)
    public String seeMessageRequest(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(3l);
        Collection<Message> allActiveUsers =
                iMessageRepo.findAllActiveUsers(byId.get());
        Contact contact = allActiveUsers.iterator().next().getContact();
        System.out.println(contact);
        allActiveUsers.forEach(x -> System.out.println(x) );
        return "home";
    }

    @RequestMapping(value="seeMessage", method= RequestMethod.GET)
    public String seeMessage(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(3l);
        Set<Message> messages = byId.get().getMessages();
        messages.forEach(x -> System.out.println(x));
        return "home";
    }

    @RequestMapping(value="insertMessage", method= RequestMethod.GET)
    public String insertMessage(Map<String, Object> model) {

        Optional<Contact> byId = iContactRepo.findById(5l);
        Contact contact = byId.get();

        Message mess1 = new Message();
        mess1.setMessage("Salut les youtres");
        mess1.setContact(contact);
        Message mess2 = new Message();
        mess2.setMessage("Salut les bougnoules");
        mess2.setContact(contact);

        contact.getMessages().add(mess1);
        contact.getMessages().add(mess2);

        // inutile de faire des save sur les 2 messages car,
        // le mode cascade = CascadeType.PERSIST est mis sur la classe Contact.
        iContactRepo.flush();

        return "home";
    }


    // supprime le contact 5. Ceci fonctionne car le mode CascadeType.REMOVE est mis.
    @RequestMapping(value="deleteContact", method= RequestMethod.GET)
    public String deleteContact(Map<String, Object> model) {
        Optional<Contact> byId = iContactRepo.findById(5l);
        Contact contact = byId.get();
        iContactRepo.delete(contact);
        return "home";
    }


}


