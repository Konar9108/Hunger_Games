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
public class Tournaments {
    @Id
    private int tournament_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = 15)
    private TypeOfGame typeOfGame;


@Transient
    List<Judges> judgesList;
@Transient
    List<Teams> teamsList;

}
