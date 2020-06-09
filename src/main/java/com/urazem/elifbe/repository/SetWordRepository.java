package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.SetWords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetWordRepository extends JpaRepository<SetWords, Integer> {
    List<SetWords> findById(int id);
}

