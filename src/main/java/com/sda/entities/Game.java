package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int match_id;


    @OneToOne
    @JoinColumn(referencedColumnName = "team_id", name = "Drużyna_1_id")
    private Team teamOne;

    @OneToOne
    @JoinColumn(referencedColumnName = "team_id", name = "Drużyna_2_id")
    private Team teamTwo;

    @OneToOne
    @JoinColumn(referencedColumnName = "team_id", name = "Zwycięzca_id")
    private Team winnerTeam;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "judge_id")
    private Judge mainJudge;

    @Column(name = "Wynik")
    private String result;

    @OneToOne
    @JoinColumn(referencedColumnName = "tournament_id", name = "Turniej_id")
    private Tournament tournament;


}
