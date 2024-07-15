package org.highload.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetUserDTO {

    private String userId;

    private String firstName;

    private String secondName;

    private LocalDate birthdate;

    private String biography;

    private String gender;

    private String city;
}
