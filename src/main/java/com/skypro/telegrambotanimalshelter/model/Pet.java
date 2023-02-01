package com.skypro.telegrambotanimalshelter.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /*
    В этом поле можно указывать вид животного: собака, кошка и т.д.
    */
    private String petKind;

    /*
    Порода животного
    */
    private String breed;

    /*
    Окрас животного
    */
    private String petColor;

    private String petName;
    private int petAge;

    /*
    Дата поступления животного
    */
    private LocalDateTime recieptDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_user_id")
    @JsonBackReference
    private User user;

    public Pet(Long id, String petKind, String breed, String petColor, String petName, int petAge, LocalDateTime recieptDate) {
        this.id = id;
        this.petKind = petKind;
        this.breed = breed;
        this.petColor = petColor;
        this.petName = petName;
        this.petAge = petAge;
        this.recieptDate = recieptDate;
    }

    public Pet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public LocalDateTime getRecieptDate() {
        return recieptDate;
    }

    public void setRecieptDate(LocalDateTime recieptDate) {
        this.recieptDate = recieptDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
