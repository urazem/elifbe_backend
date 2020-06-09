package com.urazem.elifbe.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.urazem.elifbe.model.user.User;
import lombok.Data;

@Data
public class UserDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty( "lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("age")
    private int age;

    public UserDto(int id, String username, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
    public UserDto( String username, String firstName, String lastName, String email, int age) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public UserDto() {
    }

    public static UserDto fromModel(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAge(user.getAge());
        return dto;
    }
}
