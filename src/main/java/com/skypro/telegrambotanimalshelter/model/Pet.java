package com.skypro.telegrambotanimalshelter.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
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
    private Date recieptDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    public Pet(Long id, String petKind, String breed, String petColor, String petName, int petAge, Date recieptDate) {
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

    public Date getRecieptDate() {
        return recieptDate;
    }

    public void setRecieptDate(Date recieptDate) {
        this.recieptDate = recieptDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
