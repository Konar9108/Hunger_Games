package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Judges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int judge_id;

    @Column(name = "Imię", nullable = false, length = 15)
    private String first_name;

    @Column(name = "Nazwisko", nullable = false, length = 15)
    private String last_name;

    @Column(name = "Wiek")
    private int Age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private List<Matches> matches = new LinkedList<>();


}


