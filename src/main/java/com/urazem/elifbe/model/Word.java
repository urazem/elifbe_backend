package com.urazem.elifbe.model;

import com.urazem.elifbe.model.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;

@Entity
@Data
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
    private Integer id;

    @Column(name = "original")
    private String original;

    @Column(name = "translate")
    private String translate;

    @Column(name = "transcription")
    private String transcription;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sound")
    private String sound;


    @ManyToMany(mappedBy = "words")
    private List<Vocabulary> vocabularies;

    @ManyToMany(mappedBy = "words")
    private List<SetWords> setWords;


    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public Word() {
    }

    public Word(String translate,  String transcription, String description, String sound, int setId, List<Vocabulary> vocabularies) {
        this.translate = translate;
        this.transcription = transcription;
        this.description = description;
        this.sound = sound;
        setVocabularies(vocabularies);
    }

    public void setVocabularies(List<Vocabulary> v) {
        for (Vocabulary vocabulary : v) {
            vocabulary.getWords().add(this);
            this.vocabularies.add(vocabulary);
        }
    }

    @Override
    public String toString() {
        return "Word{}";
    }
}










