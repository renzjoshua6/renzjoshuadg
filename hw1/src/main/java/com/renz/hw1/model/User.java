package com.renz.hw1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class User {

    //id. string. required. autogenerated
    //msisdn. string. required
    //password. string. required
    //firstName. string. required
    //middleName. string
    //lastName. string. required
    //email. string. valid email format. required
    //createdAt. LocalDateTime. Date/time of registration
    //lastUpdated. LocalDateTime. Date/time when the user last updated

    private String id;
    private String msisdn;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate createdAt;
    private LocalDate lastUpdated;


    public User(String id,
                String msisdn,
                String firstName,
                String middleName,
                String lastName,
                String email,
                String password,
                LocalDate createdAt,
                LocalDate lastUpdated) {

        this.id = id;
        this.msisdn = msisdn;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }
}