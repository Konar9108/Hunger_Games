package com.sda.dao;


import com.sda.entities.IDao;
import com.sda.entities.Team;
import com.sda.jdbc.Connection;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TeamDao extends Connection implements IDao<Team> {


    public TeamDao() {
    }


    @Override
    public List<Team> getAll() {
        TypedQuery<Team> query = getEntityManager().createNamedQuery("allTeams", Team.class);
        return query.getResultList();
    }


    public Team findTeamByName(String name) {
        TypedQuery query = getEntityManager().createNamedQuery("findTeamFromName", Team.class).setParameter(1, name);
        return (Team) query.getSingleResult();
    }

    public String[] getAllTeamNames(List<Team> teams) {
        String[] allTeamsNames = new String[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            allTeamsNames[i] = teams.get(i).getName();
        }
        return allTeamsNames;
    }

    public ArrayList<Team> getRandomTeams() {
        List<Team> allTeams = getAll();
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



    public void addTeam(String name) {
        Team team = new Team();
        team.setName(name);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(team);
        getEntityManager().getTransaction().commit();
    }

    public void deleteTeamFromGivenName(String name) {
        getEntityManager().getTransaction().begin();
        TypedQuery<Team> query = getEntityManager().createNamedQuery("findTeamFromName", Team.class);
        query.setParameter(1, name);
        Team team = query.getSingleResult();
        getEntityManager().remove(team);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(Team team) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(team);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    public void modifyTeam(Team team, String name) {
        team.setName(name);

        getEntityManager().getTransaction().begin();
        getEntityManager().persist(team);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

}
