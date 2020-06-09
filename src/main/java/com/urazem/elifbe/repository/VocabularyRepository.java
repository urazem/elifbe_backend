package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.Vocabulary;
import com.urazem.elifbe.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VocabularyRepository  extends JpaRepository<Vocabulary, Integer> {
    // List<Vocabulary> findById(int id);

    @Query(value = "select u from Vocabulary u where u.id = :id")
    Vocabulary findById(@Param("id") int id);

}
