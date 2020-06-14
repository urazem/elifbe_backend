package com.urazem.elifbe.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "materials")
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @Column(name = "text")
    private String text;

    @Column(name = "video")
    private String video;

    @Column(name = "img_url")
    private String imgUrl;


}
