package org.highload.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewUserDTO {

    private String userId;

    private String firstName;

    private String secondName;

    private LocalDate birthdate;

    private String biography;

    private String gender;

    private String city;

    private String password;
}
