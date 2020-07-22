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
        @NamedNativeQuery(name = "findTeamInTournament", query = "ad from tournaments_teams", resultClass = Team.class)


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


 @Transient
   private List<Team> teamList;


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

    @OneToMany(
            mappedBy = "tournament",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Tournaments_teams> tournaments_teams = new ArrayList<>();


//    public void addTeam(Team team) {
//        Tournaments_teams tournaments_teams = new Tournaments_teams(team, this);
//        tournaments_teams.add(tournaments_teams);
//        team.getTournaments().add(tournaments_teams);
//    }

//
//    public void removeTag(Tag tag) {
//        for (Iterator<PostTag> iterator = tags.iterator();
//             iterator.hasNext(); ) {
//            PostTag postTag = iterator.next();
//
//            if (postTag.getPost().equals(this) &&
//                    postTag.getTag().equals(tag)) {
//                iterator.remove();
//                postTag.getTag().getPosts().remove(postTag);
//                postTag.setPost(null);
//                postTag.setTag(null);
//            }
//        }
//    }


}
