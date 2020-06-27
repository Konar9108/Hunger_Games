package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(name = "allTeams", query = "select * from teams ORDER BY Nazwa", resultClass = Teams.class),

        @NamedNativeQuery(name = "findTeam", query = "select * from teams where team_id = ?", resultClass = Teams.class),

        @NamedNativeQuery(name = "findTeamFromName", query = "select * from teams where Nazwa = ?", resultClass = Teams.class)

})


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int team_id;


    @Column(name = "Nazwa",nullable = false,length = 30,unique = true)
    private String name;


    @Override
    public String toString() {
        return "Teams{" +
                "team_id=" + team_id +
                ", name='" + name + '\'' +
                '}';
    }
}
