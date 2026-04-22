package com.example.service;

import com.example.entity.Client;
import com.example.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClientService {

    public void addClient(Client c) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    public Client getClient(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        Client c = em.find(Client.class, id);
        em.close();
        return c;
    }

    public List<Client> getAllClients() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Client> list = em.createQuery("from Client", Client.class).getResultList();
        em.close();
        return list;
    }

    public void updateClient(Client c) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteClient(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        Client c = em.find(Client.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit();
        em.close();
    }
}