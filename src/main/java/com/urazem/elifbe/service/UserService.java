package com.urazem.elifbe.service;

import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.user.User;
import com.urazem.elifbe.model.Word;
import com.urazem.elifbe.modelDto.SetDto;
import com.urazem.elifbe.modelDto.UserDto;
import com.urazem.elifbe.modelDto.UserWithSetDto;
import com.urazem.elifbe.modelDto.VocabularyDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


public interface UserService {
    User createUser(String username, String email, String password);
    boolean existsUser(String fname, String email);
    User findByUsername(String fname);
    User findById(int id);
    User updateUser(User user);
    void deleteUser(int id);


    void addSetWords(User user, List<SetWords> setWords);
    UserWithSetDto getUserSets(int user_id);
    VocabularyDto getVocabulary(int user_id);
    UserWithSetDto getUser(String email);
    void deleteSetWords(int user_id, List<SetWords> setWords);
    List<SetDto> getSets();

    void addMaterials(SetWords setWords);
    public boolean accountExists(String email, String password);

    void addWord(int user_id, List<Word>words);
    VocabularyDto getAllVocabularyWords(int user_id);

    void saveImage(MultipartFile imageFile) throws Exception;
}
