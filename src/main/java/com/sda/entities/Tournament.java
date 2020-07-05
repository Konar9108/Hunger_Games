package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@NamedNativeQueries({
        @NamedNativeQuery(name = "addTeamByName", query = "ad from tournaments_teams", resultClass = Team.class)


})
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tournament_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = 15)
    private GameType gameType;

 @Transient
   private List<Judge> judgeList;


    @ManyToMany
    @JoinTable(name = "tournaments_teams",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
   private List<Team> teamList;

//    @ManyToMany
//    @JoinTable(name = "tournaments_matches",
//            joinColumns = @JoinColumn(name = "tournament_id"),
//            inverseJoinColumns = @JoinColumn(name = "match_id"))
    @Transient
          private List<Game> gameList = new ArrayList<>();

    @Override
    public String toString() {
        return "Tournaments{" +
                "tournament_id=" + tournament_id +
                ", typeOfGame=" + gameType +
                ", judgesList=" + judgeList +
                ", teamsList=" + teamList +
                '}';
    }
}
