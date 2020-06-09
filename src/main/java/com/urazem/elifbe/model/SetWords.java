package com.urazem.elifbe.model;

import com.urazem.elifbe.model.user.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table("set_words")
public class SetWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Column("title")
    private String title;

    @Column("crt_title")
    private String crtTitle;

    @Column("word_count")
    private int wordCount;

    @Column("image_url")
    private String imageUrl;

    @ManyToMany(mappedBy = "sets")
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(name="set_has_words",
            joinColumns=
            @JoinColumn(name="set_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="word_id", referencedColumnName="id")
    )
    private List<Word> words = new ArrayList<>();


    public List<User> getUsers() {
        return users;
    }

    public SetWords() {
    }

    public SetWords(String title, int wordCount, String imageUrl, List<User> users) {
        this.title = title;
        this.wordCount = wordCount;
        this.imageUrl = imageUrl;
        setUsers(users);
    }

    public void setUsers(List<User> users) {
        for (User u : users) {
            u.getSets().add(this);
            this.users.add(u);
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public void setSetWords(List<Word> words) {
        for (Word word : words) {
           word.getSetWords().add(this);
            this.words.add(word);
        }
    }

    public void removeSetWords(List<Word> words) {
        for (Word word : words) {
            word.getSetWords().remove(this);
            this.words.remove(word);
        }
    }

    @Override
    public String toString() {
        return "SetWords{}";
    }
}



