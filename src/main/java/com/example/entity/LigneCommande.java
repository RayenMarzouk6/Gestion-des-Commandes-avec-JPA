package com.example.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Entity;


@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Commande commande;

     // getters & setters
    public void setProduit(Produit produit) { this.produit = produit; }
    public Produit getProduit() { return produit; }

    public void setCommande(Commande commande) { this.commande = commande; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}