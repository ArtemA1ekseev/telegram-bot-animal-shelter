package com.skypro.telegrambotanimalshelter.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Dog extends Pet{
    public Dog(Long id, String petKind, String breed, String petColor, String petName, int petAge, LocalDateTime recieptDate) {
        super(id, petKind, breed, petColor, petName, petAge, recieptDate);
    }
}

