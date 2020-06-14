package com.urazem.elifbe.rest;


import com.urazem.elifbe.model.Exercise;
import com.urazem.elifbe.model.Material;
import com.urazem.elifbe.repository.ExerciseRepository;
import com.urazem.elifbe.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TraningsController {

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private MaterialRepository materialRepository;

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

    @GetMapping(path="/materials")
    @CrossOrigin("*")
    public @ResponseBody
    List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @GetMapping(path="/materials/{name}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getMaterialByName(@PathVariable String name) {
        return new ResponseEntity<>(materialRepository.findByName(name), HttpStatus.OK);
    }
}
