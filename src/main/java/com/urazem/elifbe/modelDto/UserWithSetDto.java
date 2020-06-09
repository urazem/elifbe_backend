package com.urazem.elifbe.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urazem.elifbe.model.SetWords;
import com.urazem.elifbe.model.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserWithSetDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<SetDto> sets;

    public static UserWithSetDto fromModel(User user) {
        UserWithSetDto dto = new UserWithSetDto();
        dto.setId(user.getId());
        dto.setName(user.getUsername());
        List<SetDto> setDtos = new ArrayList<>();

        for (SetWords setWords : user.getSets()) {
            setDtos.add(SetDto.fromModel(setWords));
        }
        dto.setSets(setDtos);

        return dto;
    }
}
