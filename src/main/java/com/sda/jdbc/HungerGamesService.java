package com.sda.jdbc;

import com.sda.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    public List<Judges> findAllJudges() {
        TypedQuery<Judges> query = entityManager.createNamedQuery("allJudges", Judges.class);
        return query.getResultList();
    }

    public void addTeam(String name, TypeOfGame typeOfGame) {
        Teams team = new Teams();
        team.setName(name);
        team.setTypeOfGame(typeOfGame);
        entityManager.getTransaction().begin();
        entityManager.persist(team);
        entityManager.getTransaction().commit();
    }

    public void deleteTeamFromGivenId(int teamId) {
        entityManager.getTransaction().begin();
        Teams team = entityManager.find(Teams.class, teamId);
        entityManager.remove(team);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public List<Teams> findAllTeams() {
        TypedQuery<Teams> query = entityManager.createNamedQuery("allTeams", Teams.class);
        return query.getResultList();
    }

    public String[] getAllTeamNames(List<Teams> teams) {
        String[] allTeamsNames = new String[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            allTeamsNames[i] = teams.get(i).getName();
        }
        return allTeamsNames;
    }

    public Teams findTeamById(int id) {
        TypedQuery query = entityManager.createNamedQuery("findTeam", Teams.class).setParameter(1,id);
        return (Teams)query.getSingleResult();
    }
    public List<Teams> getRandomTeams(){
        List<Teams> allTeams = this.findAllTeams();
        Random random = new Random();
        int numberOfTeams = random.nextInt(allTeams.size());
        if(numberOfTeams<2) numberOfTeams=2;
        List<Teams> teamsForTournament = new ArrayList();
        for(int i=0;i<numberOfTeams;i++){
            Teams team = allTeams.get(random.nextInt(allTeams.size()));
            teamsForTournament.add(team);
            allTeams.remove(team);
        }
        return teamsForTournament;
    }

    public void randomizeVolleyballMatchResult(Matches match) throws IndexOutOfBoundsException {

        if(match == null){
            throw new IndexOutOfBoundsException("ERROR: Empty Match");
        }
        if(match.getTypeOfGame() != TypeOfGame.VOLLEYBALL){
            throw new IndexOutOfBoundsException("ERROR: match is not a volleyball game.");
        }

        Random rand = new Random();

        int randomWinner = rand.nextInt(2);
        if(randomWinner == 0){
            match.setWinnerTeam(match.getTeamOne());
        } else {
            match.setWinnerTeam(match.getTeamTwo());
        }
        int loserScore = rand.nextInt(3);

        match.setResult("3:" + loserScore);

        entityManager.getTransaction().begin();
        entityManager.persist(match);
        entityManager.getTransaction().commit();

    }

                                     //DO SPRAWDZENIA
    public List<Judges> getRandomJudges(){
        List<Judges> allJudges = this.findAllJudges();
        Random random = new Random();
        int numberOfJudges = random.nextInt(allJudges.size());
        if(numberOfJudges<3) numberOfJudges=3;
        List<Judges> judgesForTournament = new ArrayList();
        for(int i=0;i<numberOfJudges;i++){
            Judges judge = allJudges.get(random.nextInt(allJudges.size()));
            judgesForTournament.add(judge);
            allJudges.remove(judge);
        }
        return judgesForTournament;
    }

    //DO SPRAWDZENIA
    public Tournaments generateTournamentWithRandomTeams(TypeOfGame typeOfGame){
        Tournaments tournament = new Tournaments();
        tournament.setTeamsList(getRandomTeams());
        tournament.setJudgesList(getRandomJudges());
        tournament.setTypeOfGame(typeOfGame);
        return tournament;
    }

}