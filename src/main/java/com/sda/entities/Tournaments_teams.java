package com.sda.entities;

import javax.persistence.*;

@Entity
public class Tournaments_teams {

    @EmbeddedId
    private Tournaments_teamsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("team_id")
    @JoinColumn(name="team_id", nullable=false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tournament_id")
    @JoinColumn(name="tournament_id", nullable=false)

    private Tournament tournament;

    @Column(name = "sets_won")
    private int setsWon;


    private Tournaments_teams() {}

    public Tournaments_teams(Team team, Tournament tournament) {
        this.team = team;
        this.tournament = tournament;
        this.id = new Tournaments_teamsId(team.getTeam_id(), tournament.getTournament_id());
    }

}