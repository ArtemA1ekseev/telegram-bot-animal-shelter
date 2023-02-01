package com.skypro.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Pet describes an animals which are available for adoption by Users
 * <br>
 * Properties: <b>id</b>, <b>petKind</b>, <b>breed</b>, <b>petColor</b>, <b>petName</b>,
 * <b>petAge</b>, <b>recieptDate</b>
 * @see User
 * @version 0.0.1
 * @author i.gatin
 */
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String petKind;

    private String breed;

    private String petColor;

    private String petName;
    private int petAge;

    //Date when pet registered in shelter
    private LocalDateTime recieptDate;

    /**
     * Describes which client is attached to
     * @see User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private User user;

    /**
     * Constructor - creates an instance of Pet class with parameters
     * @param id
     * @param petKind
     * @param breed
     * @param petColor
     * @param petName
     * @param petAge
     * @param recieptDate
     */
    public Pet(Long id, String petKind, String breed, String petColor, String petName, int petAge, LocalDateTime recieptDate) {
        this.id = id;
        this.petKind = petKind;
        this.breed = breed;
        this.petColor = petColor;
        this.petName = petName;
        this.petAge = petAge;
        this.recieptDate = recieptDate;
    }

    /**
     * Constructor - creates an instance of Pet class with parameters
      */
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

    public User getClient() {
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
