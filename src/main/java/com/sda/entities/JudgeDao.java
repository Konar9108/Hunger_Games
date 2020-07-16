package com.sda.entities;


import com.sda.jdbc.HungerGamesService;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class JudgeDao extends ConnectionClass implements IDao<Judge> {

    HungerGamesService service;

    public JudgeDao(HungerGamesService service) {
        this.service = service;
    }

    public JudgeDao() {
    }

    @Override
    public Judge get(long id) {
            TypedQuery query = service.getEntityManager().createNamedQuery("findJudge", Judge.class).setParameter(1, id);
            return (Judge)query.getSingleResult();

    }

    @Override
    public List<Judge> getAll() {
        return null;
    }

    @Override
    public void add(String[]params) {
        Judge judge = new Judge();
        judge.setFirst_name(params[0]);
        judge.setLast_name(params[1]);
        judge.setAge(Integer.parseInt(params[2]));
        service.getEntityManager().getTransaction().begin();
        service.getEntityManager().persist(judge);
        service.getEntityManager().getTransaction().commit();

    }

    @Override
    public void update(Judge judge, String[] params) {

    }

    @Override
    public void delete(Judge judge) {
        service.getEntityManager().getTransaction().begin();
        service.getEntityManager().remove(judge);
        service.getEntityManager().flush();
        service.getEntityManager().getTransaction().commit();

    }

    public void modifyJudge(Judge judge, String firstName, String lastName, int age) {
        judge.setAge(age);
        judge.setFirst_name(firstName);
        judge.setLast_name(lastName);
        service.getEntityManager().getTransaction().begin();
        service.getEntityManager().persist(judge);
        service.getEntityManager().flush();
        service.getEntityManager().getTransaction().commit();
    }

    public Judge findJudgeFromNameAndLastNameAndAge(String firstName, String lastName, int age) {
        TypedQuery query = service.getEntityManager().createNamedQuery("findJudgeFirstNameLastNameAge", Judge.class)
                .setParameter(1, firstName).setParameter(2, lastName).setParameter(3, age);
        return (Judge) query.getSingleResult();
    }

    public List<Judge> findAllJudges() {
        TypedQuery<Judge> query = service.getEntityManager().createNamedQuery("allJudges", Judge.class);
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
