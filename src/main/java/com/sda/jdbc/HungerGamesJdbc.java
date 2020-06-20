package com.sda.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HungerGamesJdbc {
    private EntityManagerFactory managerFactory;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private EntityManager entityManager;

    public void openConnection() {
        managerFactory = Persistence.createEntityManagerFactory("mysqlPU");
        entityManager = managerFactory.createEntityManager();

        System.out.println("Is Open " + entityManager.isOpen());

    }

    public void closeConnection() {
        entityManager.close();
        managerFactory.close();
    }



}
