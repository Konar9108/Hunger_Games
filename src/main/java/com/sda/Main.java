package com.sda;

import com.sda.entities.Judges;
import com.sda.entities.Matches;
import com.sda.entities.Teams;
import com.sda.entities.TypeOfGame;
import com.sda.jdbc.HungerGamesJdbc;



public class Main {
    public static void main(String[] args) {

        HungerGamesJdbc connection = new HungerGamesJdbc();
        connection.openConnection();

        Judges judge1 = new Judges();
        judge1.setFirst_name("Janusz");
        judge1.setLast_name("Janowicz");
        judge1.setAge(55);

        Judges judge2 = new Judges();
        judge2.setFirst_name("Roman");
        judge2.setLast_name("Romanowski");
        judge2.setAge(55);

        Judges judge3 = new Judges();
        judge3.setFirst_name("Adam");
        judge3.setLast_name("Adamowicz");
        judge3.setAge(55);

        Teams team1 = new Teams();
        team1.setName("II LO");
        team1.setTypeOfGame(TypeOfGame.VOLLEYBALL);

        Teams team2 = new Teams();
        team2.setName("VII LO");
        team2.setTypeOfGame(TypeOfGame.VOLLEYBALL);

        Teams team3 = new Teams();
        team3.setName("ZSE");
        team3.setTypeOfGame(TypeOfGame.VOLLEYBALL);

        Teams team4 = new Teams();
        team4.setName("KUCHARZE");
        team4.setTypeOfGame(TypeOfGame.VOLLEYBALL);




        Matches match1 = new Matches();
        match1.setTeamOne(team1);
        match1.setTeamTwo(team2);
        match1.setTypeOfGame(TypeOfGame.VOLLEYBALL);
        match1.setMainJudge(judge1);
        match1.setWinnerTeam(team2);
        match1.setResult("3:2");

        Matches match2 = new Matches();
        match2.setTeamOne(team3);
        match2.setTeamTwo(team4);
        match2.setTypeOfGame(TypeOfGame.VOLLEYBALL);
        match2.setMainJudge(judge3);
        match2.setWinnerTeam(team4);
        match2.setResult("3:0");








        connection.getEntityManager().getTransaction().begin();

        connection.getEntityManager().persist(judge1);
        connection.getEntityManager().persist(judge2);
        connection.getEntityManager().persist(judge3);
        connection.getEntityManager().persist(team1);
        connection.getEntityManager().persist(team2);
        connection.getEntityManager().persist(match2);
        connection.getEntityManager().persist(team3);
        connection.getEntityManager().persist(team4);
        connection.getEntityManager().persist(match2);


        connection.getEntityManager().getTransaction().commit();





        connection.closeConnection();


    }
}