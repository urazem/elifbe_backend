package com.urazem.elifbe.rest;


import com.urazem.elifbe.model.Exercise;
import com.urazem.elifbe.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TraningsController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping(path="/all")
    @CrossOrigin("*")
    public @ResponseBody
    List<Exercise> getAllTraining() {
        return exerciseRepository.findAll();
    }

    @GetMapping(path="/{training_id}")
    public @ResponseBody
    List<Exercise> getAllTraining(@PathVariable int training_id) {
        return exerciseRepository.findByTrainingId(training_id);
    }
}
