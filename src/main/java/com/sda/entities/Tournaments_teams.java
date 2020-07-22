package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

@NamedNativeQueries({


        @NamedNativeQuery(name = "findTournamentTeam", query = "SELECT * FROM tournaments_teams\n" +
                "where team_id = ? AND tournament_id = ?;", resultClass = Tournaments_teams.class)

})
public class Tournaments_teams {

    @Id
    @Column(name = "tournaments_teams_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tournamentsTeamsIid;



    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private Team team;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private Tournament tournament;

    @Column(name = "sets_won")
    private int setsWon;




    public Tournaments_teams(Team team, Tournament tournament) {
        this.team = team;
        this.tournament = tournament;
    }

}