package com.sda.dao;

import com.sda.entities.Game;
import com.sda.entities.IDao;
import com.sda.entities.Tournament;
import com.sda.entities.Tournaments_teams;
import com.sda.jdbc.Connection;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class TournamentDao extends Connection implements IDao<Tournament> {

    GameDao gameDao = new GameDao();


    // not implemented yet
    @Override
    public List<Tournament> getAll() {
        return null;
    }

    @Override
    public void delete(Tournament tournament) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(tournament);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    public void generateTournamentMatches(Tournament tournament) {

        for (int i = 0; i < tournament.getTeamList().size(); i++) {
            Tournaments_teams tt = new Tournaments_teams(tournament.getTeamList().get(i), tournament );

            getEntityManager().getTransaction().begin();
            getEntityManager().persist(tt);
            getEntityManager().getTransaction().commit();
        }


        int teamiteratorOne = 0;
        int teamiteratorTwo = 1;

        Game game;
        while(teamiteratorOne < tournament.getTeamList().size() -1) {

            while (teamiteratorTwo < tournament.getTeamList().size()) {
                game = gameDao.generateMatch(tournament, tournament.getTeamList().get(teamiteratorOne), tournament.getTeamList().get(teamiteratorTwo));
                tournament.getGameList().add(game);
                teamiteratorTwo++;
            }
            teamiteratorOne++;
            teamiteratorTwo = teamiteratorOne + 1;
        }
        Collections.shuffle(tournament.getGameList());

        getEntityManager().getTransaction().begin();
        getEntityManager().persist(tournament);
        getEntityManager().getTransaction().commit();
    }

    public List<Object[]> getResultsFromTournament(int tournamentId) {

        String sql = "SELECT t.Nazwa, count(Zwycięzca_id) FROM tournaments_teams tt left join game g on g.Zwycięzca_id = tt.team_id join team t on t.team_id = tt.team_id\n" +
                "where tt.tournament_id = " + tournamentId + "\n"+
                "group by t.nazwa, tt.tournament_id order by count(g.zwycięzca_id) desc;";

        Query query = getEntityManager().createNativeQuery(sql); //no entity mapping
        List<Object[]> list = query.getResultList();

        return list;

    }


}
