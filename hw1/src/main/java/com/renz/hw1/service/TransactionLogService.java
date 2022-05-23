package com.renz.hw1.service;

public interface TransactionLogService {

    void log(String identifier, String log);
    void displayLogs();
}
