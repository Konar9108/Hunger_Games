package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Tournaments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tournament_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = 15)
    private TypeOfGame typeOfGame;

    @ManyToMany
    @JoinTable(name = "tournaments_judges",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "judge_id"))
    List<Judges> judgesList;


    @ManyToMany
    @JoinTable(name = "tournaments_teams",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    List<Teams> teamsList;

    @ManyToMany
    @JoinTable(name = "tournaments_matches",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id"))
            List<Matches> matchesList;

    @Override
    public String toString() {
        return "Tournaments{" +
                "tournament_id=" + tournament_id +
                ", typeOfGame=" + typeOfGame +
                ", judgesList=" + judgesList +
                ", teamsList=" + teamsList +
                '}';
    }
}
