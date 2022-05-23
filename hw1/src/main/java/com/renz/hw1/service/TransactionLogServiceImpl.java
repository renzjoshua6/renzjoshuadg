package com.renz.hw1.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionLogServiceImpl implements TransactionLogService{

    private final List<String> logs = new ArrayList<>();
    @Override
    public void log(String identifier, String log) {
        logs.add("[" + LocalDateTime.now() + "] " + identifier + " : " + log);
    }

    @Override
    public void displayLogs() {
        logs.forEach(System.out::println);
    }
}
