package com.sda.jdbc;

import com.sda.entities.Judge;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {





    @Test
    void openConnection() {

Connection.getEntityManager().isOpen();
        Connection.openConnection();


        assertTrue(Connection.getEntityManager().isOpen());

    }

    @Test
    void closeConnection() {

        Connection.openConnection();

        Connection.closeConnection();


        assertFalse(Connection.getEntityManager().isOpen());

    }

    @Test
    void addJudge() {

        Connection.openConnection();

        Judge judge = new Judge();
        judge.setFirst_name("Damian");
        judge.setLast_name("Damianovic");
        judge.setAge(28);
        Connection.getEntityManager().getTransaction().begin();
        Connection.getEntityManager().persist(judge);
        Connection.getEntityManager().getTransaction().commit();

        int id = judge.getJudge_id();

        assertNotNull( Connection.getEntityManager().find(Judge.class, id));

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