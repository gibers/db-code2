package com.movedigital.entities;

import javax.persistence.*;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "message_Sequence")
  @SequenceGenerator(name = "message_Sequence", sequenceName = "seq_message", initialValue = 1, allocationSize = 1)
  private long idmes;

  private String message;

  @ManyToOne
  @JoinColumn(name = "messidcont", nullable = false)
  private Contact contact;

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public long getIdmes() {
    return idmes;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Message{" +
            "idmes=" + idmes +
            ", message='" + message + '\'' +
            ", contact=" + contact +
            '}';
  }
}
