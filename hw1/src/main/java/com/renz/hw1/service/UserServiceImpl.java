package com.renz.hw1.service;

import com.renz.hw1.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final Map<String, User> usersDB = new HashMap<>();

    private final TransactionLogService transactionLogService;
    private final EmailValidator emailValidator;

    public UserServiceImpl(TransactionLogService transactionLogService, EmailValidator emailValidator){
        this.transactionLogService = transactionLogService;
        this.emailValidator = emailValidator;

    }

    @Override
    public User register(String msisdn,
                         String firstName,
                         String middleName,
                         String lastName,
                         String email,
                         String password)
            throws InvalidUserFieldRegistrationException {

        if (msisdn.isBlank()) {
            transactionLogService.log(msisdn, "Registration Failed!");
            throw new InvalidUserFieldRegistrationException("msisdn is a REQUIRED field!");
        }

        if (usersDB.containsKey(msisdn)) {
            transactionLogService.log(msisdn, "Registration Failed!");
            throw new InvalidUserFieldRegistrationException(msisdn + " msisdn is already registered");
        }

        if (firstName.isBlank()) {
            transactionLogService.log(msisdn, "Registration Failed!");
            throw new InvalidUserFieldRegistrationException(msisdn + " First name is a REQUIRED field!");
        }

        if (lastName.isBlank()) {
            transactionLogService.log(msisdn, "Registration Failed!");
            throw new InvalidUserFieldRegistrationException(msisdn + " Last name is a REQUIRED field!");
        }

        if (email.isBlank()) {
            transactionLogService.log(msisdn, "Registration Failed!");
            throw new InvalidUserFieldRegistrationException(msisdn + " Email is a REQUIRED field!");
        } else if (!EmailValidator.isValid(email)){
            transactionLogService.log(msisdn,"Registration Failed!");
            throw new InvalidUserFieldRegistrationException("Invalid email format: " + email);
        }

        if (password.isBlank()) {
            transactionLogService.log(msisdn, "Registration Failed!");
            throw new InvalidUserFieldRegistrationException("Password is a REQUIRED field!");
        }

        User newUser = new User(
                IdGenerator.getNext(),
                msisdn,
                firstName,
                middleName,
                lastName,
                email,
                password,
                LocalDate.now(),
                LocalDate.now());

        usersDB.put(msisdn, newUser);
        transactionLogService.log(msisdn, "User Created");
        return newUser;
    }

    @Override
    public User authenticate(String msisdn, String password) throws UserNotFoundException {

        if (usersDB.containsKey(msisdn)) {
            String validatePassword = usersDB.get(msisdn).getPassword();
            if (validatePassword == password) {
                transactionLogService.log(msisdn, "Authentication Successful");
                return usersDB.get(msisdn);
            }
            transactionLogService.log(msisdn, "Authentication Failed!" );
            throw new UserNotFoundException(msisdn + ": You have provided the wrong password!");
        } transactionLogService.log(msisdn, "Authentication failed");
        throw new UserNotFoundException(msisdn + "User is not yet registered!");

        }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersDB.values());
    }

    @Override
    public void update(String msisdn,
                       String firstName,
                       String middleName,
                       String lastName,
                       String email,
                       String password)
            throws InvalidUserFieldRegistrationException, UserNotFoundException {
        if (msisdn.isBlank() || firstName.isBlank() || email.isBlank()) {
            throw new InvalidUserFieldRegistrationException("ERROR: A required input has not been filled!");
        }

        if(!usersDB.containsKey(msisdn)){
            transactionLogService.log(msisdn, "Update Failed");
            throw new UserNotFoundException("ERROR: User is not registered");
        } else {
            User newUser = usersDB.get(msisdn);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setLastUpdated(LocalDate.now());
            usersDB.put(msisdn,newUser);

            transactionLogService.log(msisdn, "User Update Success!");
        }
    }
}
