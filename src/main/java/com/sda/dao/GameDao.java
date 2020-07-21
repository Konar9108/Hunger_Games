package com.sda.dao;


import com.sda.entities.*;
import com.sda.jdbc.Connection;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

public class GameDao extends Connection implements IDao<Game> {

    JudgeDao judgeDao = new JudgeDao();


    public Game findGameById(int gameId) {
        TypedQuery query = getEntityManager().createNamedQuery("findGameById", Game.class).setParameter(1, gameId);
        return (Game) query.getSingleResult();
    }

    public void modifyGame(int gameId, String score) {
        Game game = findGameById(gameId);
        game.setResult(score);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(game);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    public Game generateMatch(Tournament tournament, Team teamOne, Team teamTwo) {
        Game match = new Game();
        Random rand = new Random();

        int randomJudgeIdFromTournament = rand.nextInt(tournament.getJudgeList().size()) + 1;

        Judge judge = judgeDao.get(randomJudgeIdFromTournament);
        match.setTournament(tournament);
        match.setMainJudge(judge);
        match.setTeamOne(teamOne);
        match.setTeamTwo(teamTwo);

        //randomizeMatchResult(match);

        getEntityManager().getTransaction().begin();
        getEntityManager().persist(match);
        getEntityManager().getTransaction().commit();

        return match;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public void delete(Game game) {

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


}
