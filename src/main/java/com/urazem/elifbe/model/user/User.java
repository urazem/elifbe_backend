package com.urazem.elifbe.model.user;

import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Vocabulary;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "user")
public class User  extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name= "email")
    private String email;

    @Column(name= "password")
    private String password;

    @Column(name = "age")
    private int age;
    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(name="user_set",
            joinColumns=    
            @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="set_id", referencedColumnName="id")
    )
    private List<SetWords> sets = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vocabulary_id", referencedColumnName = "id")
    private Vocabulary vocabulary;

    public User(){}
    public User( String username, String firstName, String lastName, String email, List<SetWords> setWords) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setSetWords(setWords);
    }
    public User(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public List<SetWords> getSets() {
        return sets;
    }

    public void setSetWords(List<SetWords> setWords) {
        for (SetWords s : setWords) {
            s.getUsers().add(this);
            this.sets.add(s);
        }
    }

    public void removeSetWords(List<SetWords> setWords) {
        for (SetWords s : setWords) {
            s.getUsers().remove(this);
            this.sets.remove(s);
        }
    }

    @Override
    public String toString() {
        return "";
    }
}