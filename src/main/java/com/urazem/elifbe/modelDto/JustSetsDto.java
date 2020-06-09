package com.urazem.elifbe.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urazem.elifbe.model.SetWords;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class JustSetsDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("word_count")
    private int wordCount;
    @JsonProperty("image")
    private String imageUrl;
    @JsonProperty("sets")
    private List<UserDto> users;
    @JsonProperty("words")
    private List<WordDto> words;

    public static List<SetDto> fromModel(List<SetWords> setWords) {
        List<SetDto> dto = new ArrayList<>();
        for (SetWords s : setWords) {

            dto.add(SetDto.fromModel(s));
        }

        return dto;
    }
}
