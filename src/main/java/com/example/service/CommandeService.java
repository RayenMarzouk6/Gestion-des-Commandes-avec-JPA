package com.example.service;

import java.util.List;

import com.example.entity.*;
import com.example.util.JpaUtil;
import jakarta.persistence.*;

public class CommandeService {

    public void createCommande(Commande commande) {

    EntityManager em = JpaUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
        tx.begin();

        double total = 0;

        for (LigneCommande ligne : commande.getLignes()) {

            Produit produit = em.find(Produit.class,
                    ligne.getProduit().getId());

            if (produit.getStock() < ligne.getQuantite()) {
                throw new RuntimeException("Stock insuffisant pour " + produit.getNom());
            }

            produit.setStock(produit.getStock() - ligne.getQuantite());

            ligne.setProduit(produit);
            ligne.setCommande(commande);

            total += produit.getPrix() * ligne.getQuantite();
        }

        em.persist(commande);

        tx.commit();

        System.out.println("TOTAL = " + total);

    } catch (Exception e) {
        tx.rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }
}


    public void deleteCommande(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();

        Commande c = em.find(Commande.class, id);
        if (c != null) em.remove(c);

        em.getTransaction().commit();
        em.close();
    }


    public void produitsPlusVendus() {

    EntityManager em = JpaUtil.getEntityManager();

    List<Object[]> results = em.createQuery("""
        SELECT l.produit.nom, SUM(l.quantite)
        FROM LigneCommande l
        GROUP BY l.produit.nom
        ORDER BY SUM(l.quantite) DESC
    """).getResultList();

    for (Object[] r : results) {
        System.out.println(r[0] + " -> " + r[1]);
        }

        em.close();
    }
}