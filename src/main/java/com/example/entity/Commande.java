package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Entity;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCommande;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LigneCommande> lignes;


    // getters & setters
    public void setClient(Client client) { this.client = client; }
    public void setDateCommande(LocalDate dateCommande) { this.dateCommande = dateCommande; }
    public List<LigneCommande> getLignes() { return lignes; }
    public void setLignes(List<LigneCommande> lignes) { this.lignes = lignes; }



    public double calculTotal() {
    double total = 0;
        if (lignes != null) {
            for (LigneCommande l : lignes) {
                total += l.getQuantite() * l.getProduit().getPrix();
            }
        }
        return total;
    }

    public Client getClient() { return client; }
    public LocalDate getDateCommande() { return dateCommande; }
}
