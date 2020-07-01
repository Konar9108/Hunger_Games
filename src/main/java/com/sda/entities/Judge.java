package com.sda.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(name = "allJudges", query = "select * from judge", resultClass = Judge.class),

        @NamedNativeQuery(name = "findJudge", query = "select * from judge where judge_id = ?", resultClass = Judge.class),

        @NamedNativeQuery(name = "findJudgeFirstNameLastNameAge", query = "select * from judge where first_name = ? AND last_name = ? AND age = ?", resultClass = Judge.class)

})


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Judge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int judge_id;

    @Column( nullable = false, length = 15)
    private String first_name;

    @Column( nullable = false, length = 15)
    private String last_name;

    @Column
    private int Age;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Game> matches = new LinkedList<>();

}


