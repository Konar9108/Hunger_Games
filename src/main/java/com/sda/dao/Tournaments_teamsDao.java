package com.sda.dao;

import com.sda.entities.*;
import com.sda.jdbc.Connection;

import javax.persistence.TypedQuery;
import java.util.List;



public class Tournaments_teamsDao extends Connection implements IDao<Tournaments_teams> {


    public Tournaments_teams findTournamentTeam(int team_id, int tournament_id) {
        TypedQuery query = getEntityManager().createNamedQuery("findTournamentTeam", Tournaments_teams.class)
                .setParameter(1, team_id).setParameter(2, tournament_id);
        return (Tournaments_teams) query.getSingleResult();
    }


    @Override
    public List<Tournaments_teams> getAll() {
        //
        return null;
    }

    @Override
    public void delete(Tournaments_teams tournaments_teams) {
//
    }
}
