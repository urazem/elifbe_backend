package com.urazem.elifbe.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Word;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("crt_title")
    private String crtTitle;
    @JsonProperty("word_count")
    private int wordCount;
    @JsonProperty("image")
    private String imageUrl;
    @JsonProperty("words")
    private List<WordDto> words;

    public static SetDto fromModel(SetWords setWords) {
        SetDto dto = new SetDto();
        dto.setId(setWords.getId());
        dto.setTitle(setWords.getTitle());
        dto.setCrtTitle(setWords.getCrtTitle());
        dto.setWordCount(setWords.getWordCount());
        dto.setImageUrl(setWords.getImageUrl());

        List<WordDto> wordDtos = new ArrayList<>();

        for (Word words : setWords.getWords()) {
            wordDtos.add(WordDto.fromModel(words));
        }
        dto.setWords(wordDtos);
        return dto;
    }
}
