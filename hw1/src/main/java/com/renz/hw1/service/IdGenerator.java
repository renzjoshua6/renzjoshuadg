package com.renz.hw1.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerator {
    public static String getNext(){;
        return UUID.randomUUID().toString();
    }
}
