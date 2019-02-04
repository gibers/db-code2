package com.movedigital.entities;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.Objects;


@Embeddable
public class Commandes {

    //    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "commande_Sequence")
//    @SequenceGenerator(name = "commande_Sequence", sequenceName = "seq_commande", initialValue = 1, allocationSize = 1)
//    private long idcomm;


    @Column(nullable = false)
    @ColumnDefault("1")
    private int idCommande;

    //  @Column(nullable = false)
    private String adresseDeLivraison;

    @Column(name = "weight", nullable = false)
    @ColumnDefault("12")
    private int poid;

    @Column(nullable = false)
    @ColumnDefault("12")
    private int height;

    @Parent
    private Contact contact;

//    public long getIdcomm() {
//        return idcomm;
//    }


    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }


    public String getAdresseDeLivraison() {
        return adresseDeLivraison;
    }

    public void setAdresseDeLivraison(String adresseDeLivraison) {
        this.adresseDeLivraison = adresseDeLivraison;
    }


    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commandes commandes = (Commandes) o;
        return idCommande == commandes.idCommande &&
                poid == commandes.poid &&
                height == commandes.height &&
                Objects.equals(adresseDeLivraison, commandes.adresseDeLivraison) &&
                Objects.equals(contact, commandes.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, adresseDeLivraison, poid, height, contact);
    }

    @Override
    public String toString() {
        return "Commandes{" +
                "idCommande=" + idCommande +
                ", adresseDeLivraison='" + adresseDeLivraison + '\'' +
                ", poid=" + poid +
                ", height=" + height +
//                ", contact=" + contact +
                '}';
    }
}
