package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int match_id;

    @Column(name = "Pierwsza drużyna",nullable = false,length = 30)
    private int first_team_id;

    @Column(name = "Druga drużyna",nullable = false,length = 30)
    private int second_team_id;

    @Column(name = "Zwycięzca",length = 30)
    private int winner_team_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type of a game", length = 15)
    private TypeOfGame typeOfGame;


}
