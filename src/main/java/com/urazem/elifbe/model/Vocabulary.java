package com.urazem.elifbe.model;

import com.urazem.elifbe.model.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "vocabulary")
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "vocabulary")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="vocabulary_words",
            joinColumns=
            @JoinColumn(name="vocabulary_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="word_id", referencedColumnName="id")
    )
    private List<Word> words = new ArrayList<>();

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        for (Word word : words) {
//            word.getVocabularies().add(this);
            this.words.add(word);
        }
    }

    public void removeWords(List<Word> words) {
        for (Word word : words) {
            word.getVocabularies().remove(this);
            this.words.remove(word);
        }
    }

    @Override
    public String toString() {
        return "Vocabulary{}";
    }
}

