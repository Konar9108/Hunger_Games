package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int match_id;


    @OneToOne
    @JoinColumn(referencedColumnName = "team_id", name = "Drużyna_1_id")
    private Teams teamOne;

    @OneToOne
    @JoinColumn(referencedColumnName = "team_id", name = "Drużyna_2_id")
    private Teams teamTwo;

    @OneToOne
    @JoinColumn(referencedColumnName = "team_id", name = "Zwycięzca_id")
    private Teams winnerTeam;

    @Enumerated(EnumType.STRING)
    @Column(name = "Typ_Gry", length = 15)
    private TypeOfGame typeOfGame;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "judge_id")
    private Judges mainJudge;

    @Column(name = "Wynik")
    private String result;

    @OneToOne
    @JoinColumn(referencedColumnName = "tournament_id", name = "Turniej_id")
    private Tournaments tournament;


}
