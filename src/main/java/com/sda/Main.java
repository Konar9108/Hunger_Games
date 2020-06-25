package com.sda;

import com.sda.entities.*;
import com.sda.jdbc.HungerGamesService;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        HungerGamesService service = new HungerGamesService();
        service.openConnection();


        service.addJudge("Bogdan", "Bogdanowicz", 25);
        service.addJudge("Janusz", "Janowicz", 76);
        service.addJudge("Roman", "Romanowski", 65);
        service.addJudge("Adam", "Adamowicz", 35);
        service.addJudge("Piotr", "Piotrowicz", 43);

        service.addTeam("BULDOŻERY", TypeOfGame.VOLLEYBALL);
        service.addTeam("II LO", TypeOfGame.VOLLEYBALL);
        service.addTeam("KUCHARZE", TypeOfGame.VOLLEYBALL);
        service.addTeam("VII LO", TypeOfGame.VOLLEYBALL);
        service.addTeam("ROLNICY", TypeOfGame.VOLLEYBALL);
        service.addTeam("SDA", TypeOfGame.VOLLEYBALL);
        service.addTeam("ZSE", TypeOfGame.VOLLEYBALL);
        service.addTeam("BACKENDOWCY", TypeOfGame.VOLLEYBALL);



        Tournaments tournament = service.generateTournamentWithRandomTeams(TypeOfGame.VOLLEYBALL);

        Teams team1 = service.findTeamById(1);
        Teams team2 = service.findTeamById(2);

        service.generateVolleyballMatch(tournament, team1, team2 );




//        service.randomizeVolleyballMatchResult(match1);
//        service.randomizeVolleyballMatchResult(match2);

//        String[] teamNames = service.getAllTeamNames(service.findAllTeams());
//        for (String teamName : teamNames) {
//            System.out.println(teamName);
//        }




        service.closeConnection();


    }
}
