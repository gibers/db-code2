package com.movedigital.entities;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class Contact {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "contact_Sequence")
  @SequenceGenerator(name = "contact_Sequence", sequenceName = "seq_contact", initialValue = 1, allocationSize = 1)
  private Long idcont;

  private String firstname;
  private String lastname;
  private String phonenumber;
  private String emailaddress;


  @ElementCollection
  @CollectionTable(
          name="Item",
          joinColumns = @JoinColumn(name="contact_idcont"))
  @Column(name="name")
  private Set<String> nameItem = new HashSet<String>();

  @ElementCollection
  @CollectionTable(name = "Item")
  @Column(name = "name")
//  @GenericGenerator(name="myGenerator",strategy="sequence")
  @CollectionId(
          columns = @Column(name = "iditem"),
          type = @Type(type="long"),
          generator = "contact_Sequence")
  private Collection<String> namesItem = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "Item")
  @OrderColumn(name = "nb_ordre")
  @Column(name = "name")
  private List<String> itemList = new ArrayList<>();

// ----------------  Commandes  -----------------------------

  @ElementCollection
  @CollectionTable(name = "Commandes", joinColumns = @JoinColumn(name = "idcont"))
  @AttributeOverride(
          name = "poid",
          column = @Column(name = "weight")
  )
  private Set<Commandes> commandes = new HashSet<>() ;

  @ElementCollection
  @CollectionTable(name = "Commandes", joinColumns = @JoinColumn(name = "idcont"))
  @CollectionId(
          columns = @Column(name = "idcomm" ),
          type = @Type(type = "long"),
          generator = "commande_Sequence"
  )
  @SequenceGenerator(name = "commande_Sequence", sequenceName = "seq_commande", initialValue = 1, allocationSize = 1)
  private Collection<Commandes> commandesMultiple = new ArrayList<>();

  @OneToMany(mappedBy = "contact", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private Set<Message> messages = new HashSet<>();

  public Set<Message> getMessages() {
    return messages;
  }

  public void setMessages(Set<Message> messages) {
    this.messages = messages;
  }

  public Collection<Commandes> getCommandesMultiple() {
        return commandesMultiple;
    }

    public void setCommandesMultiple(Collection<Commandes> commandesMultiple) {
        this.commandesMultiple = commandesMultiple;
    }

    public Set<Commandes> getCommandes() {
    return commandes;
  }
  public void setCommandes(Set<Commandes> commandes) {
    this.commandes = commandes;
  }

  public Long getIdcont() {
    return idcont;
  }

  public void setIdcont(Long idcont) {
    this.idcont = idcont;
  }


  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }


  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }


  public String getEmailaddress() {
    return emailaddress;
  }

  public void setEmailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
  }

  public Set<String> getNameItem() {
    return nameItem;
  }

  public void setNameItem(Set<String> nameItem) {
    this.nameItem = nameItem;
  }

  public Collection<String> getNamesItem() {
    return namesItem;
  }
  public void setNamesItem(Collection<String> namesItem) {
    this.namesItem = namesItem;
  }

  public List<String> getItemList() {
    return itemList;
  }

  public void setItemList(List<String> itemList) {
    this.itemList = itemList;
  }

  @Override
  public String toString() {
    return "Contact{" +
            "idcont=" + idcont +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", phonenumber='" + phonenumber + '\'' +
            ", emailaddress='" + emailaddress + '\'' +
            ", nameItem=" + nameItem +
            ", namesItem=" + namesItem +
            ", itemList=" + itemList +
            ", commandes=" + commandes +
            ", commandesMultiple=" + commandesMultiple +
            '}';
  }
}
