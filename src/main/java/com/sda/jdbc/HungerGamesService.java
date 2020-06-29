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
        Judge judge = new Judge();
        judge.setFirst_name(firstName);
        judge.setLast_name(lastName);
        judge.setAge(age);
        entityManager.getTransaction().begin();
        entityManager.persist(judge);
        entityManager.getTransaction().commit();
    }

    public void modifyJudge (Judge judge, String firstName, String lastName, int age){
judge.setAge(age);
judge.setFirst_name(firstName);
judge.setLast_name(lastName);
entityManager.getTransaction().begin();
entityManager.persist(judge);
entityManager.flush();
entityManager.getTransaction().commit();
    }

    public Judge findJudgeFromNameAndLastNameAndAge(String firstName, String lastName, int age) {
        TypedQuery query = entityManager.createNamedQuery("findJudgeFirstNameLastNameAge", Judge.class)
                .setParameter(1,firstName).setParameter(2,lastName).setParameter(3,age);
        return (Judge)query.getSingleResult();
    }


//    public void deleteJudgeFromGivenId(int judge_id) {
//        entityManager.getTransaction().begin();
//        Judges judge = entityManager.find(Judges.class, judge_id);
//        entityManager.remove(judge);
//        entityManager.flush();
//        entityManager.getTransaction().commit();
//    }

    public void deleteJudge(Judge judge) {
        entityManager.getTransaction().begin();
        entityManager.remove(judge);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }


    private Judge findJudgeById(int id) {
        TypedQuery query = entityManager.createNamedQuery("findJudge", Judge.class).setParameter(1,id);
        return (Judge)query.getSingleResult();
    }

//    public void printJudges() {
//        List<Judges> allJudges;
//        TypedQuery<Judges> query = entityManager.createNamedQuery("allJudges", Judges.class);
//        allJudges = query.getResultList();
//
//        for (Judges allJudge : allJudges) {
//            System.out.println(allJudge + "\n");
//        }
//    }

    public List<Judge> findAllJudges() {
        TypedQuery<Judge> query = entityManager.createNamedQuery("allJudges", Judge.class);
        return query.getResultList();
    }

    public void addTeam(String name) {
        Team team = new Team();
        team.setName(name);
        entityManager.getTransaction().begin();
        entityManager.persist(team);
        entityManager.getTransaction().commit();
    }

//    public void deleteTeamFromGivenId(int teamId) {
//        entityManager.getTransaction().begin();
//        Teams team = entityManager.find(Teams.class, teamId);
//        entityManager.remove(team);
//        entityManager.flush();
//        entityManager.getTransaction().commit();
//    }

    public void deleteTeamFromGivenName(String name) {

        entityManager.getTransaction().begin();
        TypedQuery<Team> query = entityManager.createNamedQuery("findTeamFromName", Team.class);
        query.setParameter(1, name);
        Team team = query.getSingleResult();
        entityManager.remove(team);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public List<Team> findAllTeams() {
        TypedQuery<Team> query = entityManager.createNamedQuery("allTeams", Team.class);
        return query.getResultList();
    }

    public String[] getAllTeamNames(List<Team> teams) {
        String[] allTeamsNames = new String[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            allTeamsNames[i] = teams.get(i).getName();
        }
        return allTeamsNames;
    }

    public String[] getAllJudgesNames(List<Judge> judges) {
        String[] allJudgesNames = new String[judges.size()];
        for (int i = 0; i < judges.size(); i++) {
            allJudgesNames[i] = (judges.get(i).getFirst_name() + " " + judges.get(i).getLast_name() + " " + judges.get(i).getAge());
        }
        return allJudgesNames;
    }

//    public Teams findTeamById(int id) {
//        TypedQuery query = entityManager.createNamedQuery("findTeam", Teams.class).setParameter(1,id);
//        return (Teams)query.getSingleResult();
//    }

    public Team getRandomTeam(List<Team> teams){
        List<Team> allTeams = teams;
        Random random = new Random();
        Team team = allTeams.get(random.nextInt(allTeams.size()));
        return team;
    }

    public Team findTeamByName(String name) {
        TypedQuery query = entityManager.createNamedQuery("findTeamFromName", Team.class).setParameter(1,name);
        return (Team)query.getSingleResult();
    }

    public void modifyTeam (Team team, String name){
        team.setName(name);

        entityManager.getTransaction().begin();
        entityManager.persist(team);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }



    public ArrayList<Team> getRandomTeams(){
        List<Team> allTeams = this.findAllTeams();
        Random random = new Random();
        int numberOfTeams = random.nextInt(allTeams.size());
        if(numberOfTeams<2) numberOfTeams=2;
        ArrayList<Team> teamForTournament = new ArrayList();
        for(int i=0;i<numberOfTeams;i++){
            Team team = allTeams.get(random.nextInt(allTeams.size()));
            teamForTournament.add(team);
            allTeams.remove(team);
        }
        return teamForTournament;
    }

    public void randomizeMatchResult(Game match) throws IndexOutOfBoundsException {

        if(match == null){
            throw new IndexOutOfBoundsException("ERROR: Empty Match");
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


    }

    public void generateMatch(Tournament tournament, Team teamOne, Team teamTwo){
        Game match = new Game();
        Random rand = new Random();

        int randomJudgeIdFromTournament = rand.nextInt(tournament.getJudgeList().size());

        Judge judge = findJudgeById(randomJudgeIdFromTournament);
        match.setTournament(tournament);
        match.setMainJudge(judge);
        match.setTeamOne(teamOne);
        match.setTeamTwo(teamTwo);

        randomizeMatchResult(match);

        entityManager.getTransaction().begin();
        entityManager.persist(match);
        entityManager.getTransaction().commit();

    }



    public void generateTournamentMatches(Tournament tournament){
    List<List<Team>> DimensionalListofTeams;


    }



    public List<Judge> getRandomJudges(){
        List<Judge> allJudges = this.findAllJudges();
        Random random = new Random();
        int numberOfJudges = random.nextInt(allJudges.size());
        if(numberOfJudges<3) numberOfJudges=3;
        List<Judge> judgeForTournament = new ArrayList();
        for(int i=0;i<numberOfJudges;i++){
            Judge judge = allJudges.get(random.nextInt(allJudges.size()));
            judgeForTournament.add(judge);
            allJudges.remove(judge);
        }
        return judgeForTournament;
    }

    //DO SPRAWDZENIA
    public Tournament generateTournamentWithRandomTeams(GameType gameType){
        Tournament tournament = new Tournament();
        tournament.setTeamList(getRandomTeams());
        tournament.setJudgeList(getRandomJudges());
        tournament.setGameType(gameType);

        entityManager.getTransaction().begin();
        entityManager.persist(tournament);
        entityManager.getTransaction().commit();


        return tournament;
    }

}