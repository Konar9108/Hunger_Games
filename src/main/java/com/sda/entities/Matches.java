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

    @OneToMany
    private List<Teams> teams;


    @Column(name = "Zwyciezca" , length = 30)
    private int winner_team_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TypeOfGame", length = 15)
    private TypeOfGame typeOfGame;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "judge_id")
    private Judges mainJudge;


}
