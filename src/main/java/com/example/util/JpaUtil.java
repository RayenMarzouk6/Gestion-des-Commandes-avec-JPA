package com.example.util;

import jakarta.persistence.*;

public class JpaUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("commandePU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}