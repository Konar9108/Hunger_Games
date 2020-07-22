package com.sda.entities;

import lombok.Getter;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Tournaments_teamsId implements Serializable {

private int tournament_id;
private int team_id;

    public Tournaments_teamsId(int tournament_id, int team_id) {
        this.tournament_id = tournament_id;
        this.team_id = team_id;
    }





}