package com.movedigital.entities;

import javax.persistence.*;

@Entity
public class Item {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "item_Sequence")
  @SequenceGenerator(name = "item_Sequence", sequenceName = "seq_item", initialValue = 1, allocationSize = 1)
  private Long iditem;

  private String name;

  private Long contact_idcont;

//  private int itemlist_order;
  private int nb_ordre;

//  private Contact contact;


  public Long getIditem() {
    return iditem;
  }

  public void setIditem(Long iditem) {
    this.iditem = iditem;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Long getContact_idcont() {
    return contact_idcont;
  }

  public void setContact_idcont(Long contact_idcont) {
    this.contact_idcont = contact_idcont;
  }

  public int getItemlist_order() {
    return nb_ordre;
  }

  public void setItemlist_order(int itemlist_order) {
    this.nb_ordre = itemlist_order;
  }
}
