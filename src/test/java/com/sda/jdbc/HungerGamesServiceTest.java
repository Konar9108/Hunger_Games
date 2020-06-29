package com.sda.jdbc;

import com.sda.entities.Judge;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HungerGamesServiceTest {


    HungerGamesService hungerGamesService = new HungerGamesService();


    @Test
    void openConnection() {


        hungerGamesService.openConnection();


        assertTrue(hungerGamesService.getEntityManager().isOpen());

    }

    @Test
    void closeConnection() {

        hungerGamesService.openConnection();

        hungerGamesService.closeConnection();


       assertFalse(hungerGamesService.getEntityManager().isOpen());

    }

    @Test
    void addJudge() {

        hungerGamesService.openConnection();

        Judge judge = new Judge();
        judge.setFirst_name("Damian");
        judge.setLast_name("Damianovic");
        judge.setAge(28);
        hungerGamesService.getEntityManager().getTransaction().begin();
        hungerGamesService.getEntityManager().persist(judge);
        hungerGamesService.getEntityManager().getTransaction().commit();

        int id = judge.getJudge_id();

        assertNotNull( hungerGamesService.getEntityManager().find(Judge.class, id));

    }


    @Test
    void findAllJudges() {
    }

    @Test
    void addTeam() {
    }

    @Test
    void findAllTeams() {
    }

    @Test
    void findTeamById() {
    }

    @Test
    void getRandomTeams() {
    }

    @Test
    void getRandomJudges() {
    }
}