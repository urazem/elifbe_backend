package com.urazem.elifbe.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Vocabulary;
import com.urazem.elifbe.model.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class VocabularyDto {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("words")
    private List<WordDto> words;

    public static VocabularyDto fromModel(Vocabulary v) {
        VocabularyDto dto = new VocabularyDto();
        dto.setId(v.getId());
        dto.setName(v.getName());
        List<WordDto> wordDtos = new ArrayList<>();

        for (Word word : v.getWords()) {
            wordDtos.add(WordDto.fromModel(word));
        }
        dto.setWords(wordDtos);

        return dto;
    }
}
