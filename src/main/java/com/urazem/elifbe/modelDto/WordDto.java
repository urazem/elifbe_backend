package com.urazem.elifbe.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.Word;
import lombok.Data;

@Data
public class WordDto {

    @JsonProperty("id")
    private int id;
    @JsonProperty("original")
    private String original;
    @JsonProperty("translate")
    private String translate;

    @JsonProperty("transcription")
    private String transcription;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("sound")
    private String sound;

    public static WordDto fromModel(Word word) {
        WordDto dto = new WordDto();
        dto.setId(word.getId());
        dto.setOriginal(word.getOriginal());
        dto.setTranslate(word.getTranslate());
        dto.setTranscription(word.getTranscription());
        dto.setDescription(word.getDescription());
        dto.setImageUrl(word.getImageUrl());
        dto.setSound(word.getSound());
        return dto;
    }
}