package com.renz.hw1;

import com.renz.hw1.model.User;
import com.renz.hw1.service.InvalidUserFieldRegistrationException;
import com.renz.hw1.service.TransactionLogService;
import com.renz.hw1.service.UserNotFoundException;
import com.renz.hw1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserService userService;
    private final TransactionLogService transactionLogService;

    public Bootstrap(UserService userService, TransactionLogService transactionLogService) {
        this.userService = userService;
        this.transactionLogService = transactionLogService;
    }

    @Override
    public void run(String... args) throws Exception {
        addSeparator("Successful registration and authentication");

        userService.register("639175861661", "John", "Nuary", "Juan", "j.doe@test.com", "mypassword123");
        User john = userService.authenticate("639175861661", "mypassword123");

        System.out.println("Login success! Welcome, " + john.getFirstName() + "!");

        addSeparator("Invalid email format");

        try {
            userService.register("639175861662", "Feb", "Buary", "Por", "feb2022", "mypassword456");
        } catch (InvalidUserFieldRegistrationException e) {
            System.out.println("ERROR! " + e.getMessage());
        }

        addSeparator("Checkpoint for current user registration counter");

        System.out.println("At this point, we have " + userService.getAllUsers().size() + " registered user/s");

        addSeparator("Failed authentication");

        try {
            userService.register("639175861663", "Kelly", "", "Klarkzon", "kelly@gmail.com", "thebrownfox");
            userService.authenticate("639175861663", "mypassword123");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        addSeparator("Checkpoint for current user registration counter");

        System.out.println("At this point, we have " + userService.getAllUsers().size() + " registered user/s");

        addSeparator("Empty lastName. IMPORTANT! exception message must identify what field is invalid/empty");

        try {
            userService.register("639175861664", "Teri", "Teri", "", "teri@bol.com", "mypassword456");
        } catch (InvalidUserFieldRegistrationException e) {
            System.out.println("ERROR! " + e.getMessage());
        }

        addSeparator("User already registered");

        try {
            userService.register("639175861661", "John", "Nuary", "Juan", "j.doe@test.com", "mypassword123");
        } catch (InvalidUserFieldRegistrationException e) {
            System.out.println("ERROR! " + e.getMessage());
        }

        addSeparator("Checkpoint for current user registration counter");

        System.out.println("Here are the current users: ");
        userService.getAllUsers().forEach(user -> System.out.println("ID: " + user.getId() + "First Name: " + user.getFirstName() + ", Registration Date: " + user.getCreatedAt() + ", Last Updated: " + user.getLastUpdated()));

        addSeparator("Successful user update");

        userService.update("639175861661", "John", "", "Juan", "j.doe@test.com", "$trong3rP@ssw0rd");
        john = userService.authenticate("639175861661", "$trong3rP@ssw0rd");

        System.out.println("User " + john.getMsisdn() + " updated!");
        System.out.println(john);

        addSeparator("Displaying all transactions/action happened");

        transactionLogService.displayLogs();

        addSeparator("Login attempt. It must be added in the transaction logs");

        userService.authenticate("639175861661", "$trong3rP@ssw0rd");
        transactionLogService.displayLogs();
    }

    static int ctr;
    static void addSeparator(String note) {
        System.out.println();
        System.out.println("===== " + ++ctr + ". " + note + " =====");
        System.out.println();
    }
}