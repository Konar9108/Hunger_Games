package com.sda.dao;


import com.sda.jdbc.Connection;
import com.sda.entities.IDao;
import com.sda.entities.Judge;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JudgeDao extends Connection implements IDao<Judge> {

    
    public Judge get(int id) {
            TypedQuery query = getEntityManager().createNamedQuery("findJudge", Judge.class).setParameter(1, id);
            return (Judge)query.getSingleResult();

    }

    @Override
    public List<Judge> getAll() {
        return null;
    }


    public void addJudge(String firstName, String lastName, int age) {
        Judge judge = new Judge();
        judge.setFirst_name(firstName);
        judge.setLast_name(lastName);
        judge.setAge(age);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(judge);
        getEntityManager().getTransaction().commit();
    }


    @Override
    public void delete(Judge judge) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(judge);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();

    }

    public void modifyJudge(Judge judge, String firstName, String lastName, int age) {
        judge.setAge(age);
        judge.setFirst_name(firstName);
        judge.setLast_name(lastName);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(judge);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    public Judge findJudgeFromNameAndLastNameAndAge(String firstName, String lastName, int age) {
        TypedQuery query = getEntityManager().createNamedQuery("findJudgeFirstNameLastNameAge", Judge.class)
                .setParameter(1, firstName).setParameter(2, lastName).setParameter(3, age);
        return (Judge) query.getSingleResult();
    }

    public List<Judge> findAllJudges() {
        TypedQuery<Judge> query = getEntityManager().createNamedQuery("allJudges", Judge.class);
        return query.getResultList();
    }

    public String[] getAllJudgesNames(List<Judge> judges) {
        String[] allJudgesNames = new String[judges.size()];
        for (int i = 0; i < judges.size(); i++) {
            allJudgesNames[i] = (judges.get(i).getFirst_name() + " " + judges.get(i).getLast_name() + " " + judges.get(i).getAge());
        }
        return allJudgesNames;
    }

    public List<Judge> getRandomJudges() {
        List<Judge> allJudges = findAllJudges();
        Random random = new Random();
        int numberOfJudges = random.nextInt(allJudges.size());
        if (numberOfJudges < 3) numberOfJudges = 3;
        List<Judge> judgeForTournament = new ArrayList();
        for (int i = 0; i < numberOfJudges; i++) {
            Judge judge = allJudges.get(random.nextInt(allJudges.size()));
            judgeForTournament.add(judge);
            allJudges.remove(judge);
        }
        return judgeForTournament;
    }



}
