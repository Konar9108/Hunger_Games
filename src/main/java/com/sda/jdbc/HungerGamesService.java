package com.sda.jdbc;

import com.sda.entities.Judges;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class HungerGamesService {

    private EntityManagerFactory managerFactory;
    private EntityManager entityManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void openConnection() {
        managerFactory = Persistence.createEntityManagerFactory("mysqlPU");
        entityManager = managerFactory.createEntityManager();

        System.out.println("Is Open " + entityManager.isOpen());

    }

    public void closeConnection() {
        entityManager.close();
        managerFactory.close();
    }

    public void addJudge(String firstName, String lastName, int age) {
        Judges judge = new Judges();
        judge.setFirst_name(firstName);
        judge.setLast_name(lastName);
        judge.setAge(age);
        entityManager.getTransaction().begin();
        entityManager.persist(judge);
        entityManager.getTransaction().commit();
    }

    public void deleteJudgeFromGivenId(int judge_id) {
        entityManager.getTransaction().begin();
        Judges judge = entityManager.find(Judges.class, judge_id);
        entityManager.remove(judge);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void printJudges() {
        List<Judges> allJudges;
        TypedQuery<Judges> query = entityManager.createNamedQuery("allJudges", Judges.class);
        allJudges = query.getResultList();

        for (Judges allJudge : allJudges) {
            System.out.println(allJudge + "\n");

        }

    }


}