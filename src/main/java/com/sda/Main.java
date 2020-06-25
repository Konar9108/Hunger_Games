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

        service.generateVolleyballMatch(tournament, service.findTeamById(1), service.findTeamById(2) );


        new Window().showGuiWindow();


       // service.closeConnection();


    }
}
