package com.sda.jdbc;

import com.sda.entities.*;
import org.hibernate.SQLQuery;

import javax.persistence.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

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

    public void modifyJudge(Judge judge, String firstName, String lastName, int age) {
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
                .setParameter(1, firstName).setParameter(2, lastName).setParameter(3, age);
        return (Judge) query.getSingleResult();
    }



    public void deleteJudge(Judge judge) {
        entityManager.getTransaction().begin();
        entityManager.remove(judge);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }


    private Judge findJudgeById(int id) {
        TypedQuery query = entityManager.createNamedQuery("findJudge", Judge.class).setParameter(1, id);
        return (Judge) query.getSingleResult();
    }


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

    public Team getRandomTeam(List<Team> teams) {
        List<Team> allTeams = teams;
        Random random = new Random();
        Team team = allTeams.get(random.nextInt(allTeams.size()));
        return team;
    }

    public Team findTeamByName(String name) {
        TypedQuery query = entityManager.createNamedQuery("findTeamFromName", Team.class).setParameter(1, name);
        return (Team) query.getSingleResult();
    }
    public Game findGameById(int gameId) {
        TypedQuery query = entityManager.createNamedQuery("findGameById", Game.class).setParameter(1, gameId);
        return (Game) query.getSingleResult();
    }
    public void modifyTeam(Team team, String name) {
        team.setName(name);

        entityManager.getTransaction().begin();
        entityManager.persist(team);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public List<Object[]> getResultsFromTournament(int tournamentId) {


        String sql = "SELECT t.Nazwa, count(Zwycięzca_id) FROM tournaments_teams tt left join game g on g.Zwycięzca_id = tt.team_id join team t on t.team_id = tt.team_id\n" +
                "where tt.tournament_id = " + tournamentId + "\n"+
                "group by t.nazwa, tt.tournament_id order by count(g.zwycięzca_id) desc;";


        Query query = entityManager.createNativeQuery(sql); //no entity mapping
        List<Object[]> list = query.getResultList();

//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i)[0] + " ");
//            System.out.println(list.get(i)[1]);
//        }
return list;

    }

    public void modifyGame(int gameId, String score) {
        Game game = findGameById(gameId);
        game.setResult(score);
        entityManager.getTransaction().begin();
        entityManager.persist(game);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public ArrayList<Team> getRandomTeams() {
        List<Team> allTeams = findAllTeams();
        Random random = new Random();
        int numberOfTeams = random.nextInt(allTeams.size());
        if (numberOfTeams < 3) numberOfTeams = 3;
        ArrayList<Team> teamForTournament = new ArrayList();
        for (int i = 0; i < numberOfTeams; i++) {
            Team team = allTeams.get(random.nextInt(allTeams.size()));
            teamForTournament.add(team);
            allTeams.remove(team);
        }
        return teamForTournament;
    }

    public void randomizeMatchResult(Game match) throws IndexOutOfBoundsException {

        if (match == null) {
            throw new IndexOutOfBoundsException("ERROR: Empty Match");
        }


        Random rand = new Random();

        int randomWinner = rand.nextInt(2);
        if (randomWinner == 0) {
            match.setWinnerTeam(match.getTeamOne());
        } else {
            match.setWinnerTeam(match.getTeamTwo());
        }
        int loserScore = rand.nextInt(3);

        match.setResult("3:" + loserScore);


    }

    public Game generateMatch(Tournament tournament, Team teamOne, Team teamTwo) {
        Game match = new Game();
        Random rand = new Random();

        int randomJudgeIdFromTournament = rand.nextInt(tournament.getJudgeList().size()) + 1;

        Judge judge = findJudgeById(randomJudgeIdFromTournament);
        match.setTournament(tournament);
        match.setMainJudge(judge);
        match.setTeamOne(teamOne);
        match.setTeamTwo(teamTwo);

        //randomizeMatchResult(match);

        entityManager.getTransaction().begin();
        entityManager.persist(match);
        entityManager.getTransaction().commit();

        return match;
    }


    public void generateTournamentMatches(Tournament tournament) {


        int teamiteratorOne = 0;
        int teamiteratorTwo = 1;

        Game game;
        while(teamiteratorOne < tournament.getTeamList().size() -1) {

            while (teamiteratorTwo < tournament.getTeamList().size()) {
                game = generateMatch(tournament, tournament.getTeamList().get(teamiteratorOne), tournament.getTeamList().get(teamiteratorTwo));
                tournament.getGameList().add(game);
                teamiteratorTwo++;
            }
            teamiteratorOne++;
            teamiteratorTwo = teamiteratorOne + 1;
        }
Collections.shuffle(tournament.getGameList());

        entityManager.getTransaction().begin();
        entityManager.persist(tournament);
        entityManager.getTransaction().commit();
    }


    public List<Judge> getRandomJudges() {
        List<Judge> allJudges = this.findAllJudges();
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

    //DO SPRAWDZENIA
    public Tournament generateTournamentWithRandomTeams(GameType gameType) {
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