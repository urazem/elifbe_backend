package com.urazem.elifbe.service.impl;

import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Vocabulary;
import com.urazem.elifbe.model.Word;
import com.urazem.elifbe.model.user.Status;
import com.urazem.elifbe.model.user.User;
import com.urazem.elifbe.modelDto.JustSetsDto;
import com.urazem.elifbe.modelDto.SetDto;
import com.urazem.elifbe.modelDto.UserWithSetDto;
import com.urazem.elifbe.modelDto.VocabularyDto;
import com.urazem.elifbe.repository.SetWordRepository;
import com.urazem.elifbe.repository.UserRepository;
import com.urazem.elifbe.repository.VocabularyRepository;
import com.urazem.elifbe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SetWordRepository setWordRepository;
    private final VocabularyRepository vocabularyRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VocabularyRepository vocabularyRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vocabularyRepository = vocabularyRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String fname){
        return userRepository.findByUsername(fname);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        User std = userRepository.getOne(user.getId());
        if (std != null) {
            std.setUsername(user.getUsername());
            std.setFirstName(user.getFirstName());
            std.setLastName(user.getLastName());
            std.setEmail(user.getEmail());
            std.setAge(user.getAge());

            return userRepository.save(std);
        }
        return null;
    }
    @Override
    public boolean existsUser(String fname, String email){
        if(userRepository.existsByUsernameAndEmail(fname, email)){
            return true;
        }else return false;
    }
    @Override
    public boolean accountExists(String email, String password){
        if(userRepository.existsByEmailAndPassword(email, password)){
            return true;
        }else return false;
    }

    @Override
    public void addWord(int user_id, List<Word>words) {
        User user = userRepository.findById(user_id);
        if (user != null) {
            int vocabulary_id = user.getVocabulary().getId();
            Vocabulary vocabulary = vocabularyRepository.findById(vocabulary_id);
            vocabulary.setWords(words);
            vocabularyRepository.save(vocabulary);
        }
    }

    @Override
    public VocabularyDto getAllVocabularyWords(int user_id) {
        User user = userRepository.findById(user_id);
        if (user != null) {
            int vocabulary_id = user.getVocabulary().getId();
            Vocabulary vocabulary = vocabularyRepository.findById(vocabulary_id);
            return  VocabularyDto.fromModel(vocabulary);
        }
        return null;
    }

    @Override
    public void saveImage(MultipartFile imageFile) throws Exception {
        String folder = "/photos/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserWithSetDto getUser(String email) {
       return UserWithSetDto.fromModel(userRepository.findByEmail(email));
    }

    @Override
    public void addSetWords(User u, List<SetWords> setWords) {
        User user = userRepository.findById(u.getId());
        user.setSetWords(setWords);
        userRepository.save(user);
    }
    @Override
    public void deleteSetWords(int user_id, List<SetWords> setWords) {
        User user = userRepository.findById(user_id);
        user.removeSetWords(setWords);
        userRepository.save(user);
    }
    @Override
    public UserWithSetDto getUserSets(int user_id){
        return UserWithSetDto.fromModel(userRepository.findById(user_id));
    }

    @Override
    public VocabularyDto getVocabulary(int user_id) {
        User user = userRepository.findById(user_id);
        return VocabularyDto.fromModel(user.getVocabulary());
    }

    @Override
    public List<SetDto> getSets(){
        return JustSetsDto.fromModel(setWordRepository.findAll());
    }

    @Override
    public void addMaterials(SetWords setWords) {

    }



}
