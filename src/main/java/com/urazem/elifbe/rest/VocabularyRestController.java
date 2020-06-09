package com.urazem.elifbe.rest;

import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Word;
import com.urazem.elifbe.model.user.User;
import com.urazem.elifbe.repository.SetWordRepository;
import com.urazem.elifbe.repository.WordRepository;
import com.urazem.elifbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user/sets")
public class VocabularyRestController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    private SetWordRepository setWordRepository;

    @GetMapping(path = "/userSets/{user_id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getUserSets(@PathVariable int user_id) {
        return new ResponseEntity<>(userService.getUserSets(user_id), HttpStatus.OK);

    }
    @GetMapping(path = "/all")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getSets() {
        return new ResponseEntity<>(userService.getSets(), HttpStatus.OK);
    }

    @GetMapping(path = "/wordsAll/{user_id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getVocabularyWords(@PathVariable int user_id) {

        return new ResponseEntity<>(userService.getAllVocabularyWords(user_id), HttpStatus.OK);
    }


    @PostMapping(path = "/add/{id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> addSet(@PathVariable int id, @RequestBody User user) {
        List<SetWords> sets = setWordRepository.findById(id);
        userService.addSetWords(user, sets);
        return new ResponseEntity<>("Sets added successfully", HttpStatus.OK);
    }

    @PostMapping(path = "/addWords/{user_id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> addWordInVocabulary(@PathVariable int user_id, @RequestBody List<Word> words) {
        userService.addWord(user_id, words);
        return new ResponseEntity<>("Words added successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{user_id}/{id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> deleteSet(@PathVariable int user_id, @PathVariable int id) {
        List<SetWords> sets = setWordRepository.findById(id);
        userService.deleteSetWords(user_id, sets);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}