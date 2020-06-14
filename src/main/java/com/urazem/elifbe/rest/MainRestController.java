package com.urazem.elifbe.rest;

import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.user.User;
import com.urazem.elifbe.modelDto.UserDto;
import com.urazem.elifbe.modelDto.VocabularyDto;
import com.urazem.elifbe.repository.SetWordRepository;
import com.urazem.elifbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import javax.naming.AuthenticationException;
import javax.naming.ConfigurationException;
import javax.servlet.MultipartConfigElement;
import java.util.List;
@RestController // This means that this class is a Controller
    @RequestMapping(path="/api/v1/user") // This means URL's start with /demo (after Application path)
public class MainRestController {

    // This means to get the bean called userRepository
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SetWordRepository setWordRepository;



    @GetMapping(path = "vocabulary/{user_id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getVocabulary(@PathVariable int user_id) {
        User user = userService.findById(user_id);

        if (user != null) {
            VocabularyDto vocabularyDto = userService.getVocabulary(user_id);
            return new ResponseEntity<>(vocabularyDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{username}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);

        if (user != null) {
            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge());

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/save")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> getUserByUsername(@RequestBody User user) {
        User updateUser = userService.updateUser(user);

        if (updateUser != null) {
            UserDto userDto = new UserDto(updateUser.getId(), updateUser.getUsername(), updateUser.getFirstName(), updateUser.getLastName(), updateUser.getEmail(), updateUser.getAge());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(path = "/delete/{user_id}/{id}")
    @CrossOrigin("*")
    public @ResponseBody
    ResponseEntity<Object> deleteSet(@PathVariable int user_id, @PathVariable int id) {
        List<SetWords> sets = setWordRepository.findById(id);
        userService.deleteSetWords(user_id, sets);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/uploadImage",  consumes = "multipart/form-data")
    @CrossOrigin("*")
    public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile imageFile)throws AuthenticationException, ConfigurationException {

        try {
            userService.saveImage(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(null);

    }

}