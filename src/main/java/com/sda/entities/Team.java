package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(name = "allTeams", query = "select * from team ORDER BY Nazwa", resultClass = Team.class),

        @NamedNativeQuery(name = "findTeam", query = "select * from team where team_id = ?", resultClass = Team.class),

        @NamedNativeQuery(name = "findTeamFromName", query = "select * from team where Nazwa = ?", resultClass = Team.class)

})


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int team_id;


    @Column(name = "Nazwa",nullable = false,length = 30,unique = true)
    private String name;


    @Override
    public String toString() {
        return "Team{" +
                "team_id=" + team_id +
                ", name='" + name + '\'' +
                '}';
    }
}
