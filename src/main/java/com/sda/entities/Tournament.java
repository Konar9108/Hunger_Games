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
public class Tournament {
    @Id
    private int tournament_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type of a game", length = 15)
    private TypeOfGame typeOfGame;



    List<Judge> judgeList;
    List<Team> teamList;

}
