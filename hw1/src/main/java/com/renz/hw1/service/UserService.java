package com.renz.hw1.service;

import com.renz.hw1.model.User;

import java.util.List;
public interface UserService {

 User register(String msisdn,
               String firstName,
               String middleName,
               String lastName,
               String email,
               String password)
         throws InvalidUserFieldRegistrationException;

 User authenticate(String msisdn,
                   String password)
         throws UserNotFoundException;

 List<User> getAllUsers();

 void update(String msisdn,
             String firstName,
             String middleName,
             String lastName,
             String email,
             String password)
         throws InvalidUserFieldRegistrationException, UserNotFoundException;
}
