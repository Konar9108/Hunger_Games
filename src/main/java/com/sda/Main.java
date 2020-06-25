package com.sda;

import com.sda.entities.*;
import com.sda.jdbc.HungerGamesService;

import java.util.List;


public class Main {
    public static void main(String[] args) {

//        HungerGamesService service = new HungerGamesService();
//        service.openConnection();

//        Tournaments tournament = service.generateTournamentWithRandomTeams(TypeOfGame.VOLLEYBALL);
//
//        service.generateVolleyballMatch(tournament, service.findTeamById(1), service.findTeamById(2) );
//        service.closeConnection();


        Window window = new Window();
        window.showGuiWindow();


    }
}
