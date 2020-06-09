package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository  extends JpaRepository<Word, Integer> {
    List<Word> findById(int id);
//    List<Word> findWordsBySetId(Integer id);
}
