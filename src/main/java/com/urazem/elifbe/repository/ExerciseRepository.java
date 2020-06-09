package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    List<Exercise> findByTrainingId(int id);
}
