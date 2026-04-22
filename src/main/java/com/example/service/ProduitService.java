package com.example.service;

import com.example.entity.Produit;
import com.example.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProduitService {

    public void addProduit(Produit p) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public List<Produit> getAllProduits() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Produit> list = em.createQuery("from Produit", Produit.class).getResultList();
        em.close();
        return list;
    }

    public void updateProduit(Produit p) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteProduit(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Produit p = em.find(Produit.class, id);
        if (p != null) em.remove(p);
        em.getTransaction().commit();
        em.close();
    }
}