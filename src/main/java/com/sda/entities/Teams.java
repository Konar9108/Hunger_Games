package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int team_id;

    @Column(name = "Nazwa",nullable = false,length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = 15)
    private TypeOfGame typeOfGame;



}
