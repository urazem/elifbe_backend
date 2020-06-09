package com.urazem.elifbe.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "exercise")
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE) private Integer id;

    @Column(name = "name")
    private String exerciseName;

    @Column(name = "path")
    private String pathName;

    @Column(name = "description")
    private String exerciseDescription;

    @Column(name = "word_count")
    private int countWord;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_id")
    private Training training;
}
