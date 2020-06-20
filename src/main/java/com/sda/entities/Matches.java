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
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int match_id;

    @OneToMany(mappedBy = "team_id")
    private List<Teams> teams_id;


    @Column(name = "Zwyciezca" , length = 30)
    private int winner_team_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TypeOfGame", length = 15)
    private TypeOfGame typeOfGame;

    @ManyToOne(targetEntity = Judges.class)
    private Judges mainJudge;


}
