package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(name = "allJudges", query = "select * from judges", resultClass = Judges.class),

        @NamedNativeQuery(name = "findJudge", query = "select * from judges where judge_id = ?", resultClass = Judges.class)

})


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

    @Override
    public String toString() {
        return "Judges{" +
                "judge_id=" + judge_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", Age=" + Age +
                ", matches=" + matches +
                '}';
    }
}


