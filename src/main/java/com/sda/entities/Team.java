package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int team_id;
    @Column(name = "Nazwa drużyny",nullable = false,length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type of a game", length = 15)
    private TypeOfGame typeOfGame;



}
