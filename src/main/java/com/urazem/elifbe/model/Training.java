package com.urazem.elifbe.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "training")
public class Training implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter(AccessLevel.PACKAGE)
        private Integer id;

        @Column(name = "training_name")
        private String trainingName;

        @Column(name = "training_description")
        private String trainingDescription;


        
}
