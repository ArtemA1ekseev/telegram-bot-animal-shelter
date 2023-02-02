package com.skypro.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Pet describes an animals which are available for adoption by Users
 * <br>
 * Properties: <b>id</b>, <b>petKind</b>, <b>petBreed</b>, <b>petColor</b>, <b>petName</b>,
 * <b>petAge</b>, <b>petRecieptDate</b>
 * @see User
 * @version 0.0.1
 * @author i.gatin
 */
@Entity
@Table(name = "shelter_pet")
public class Pet {
    @Id
    @Column(name = "shelter_pet_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "shelter_pet_kind")
    private String petKind;

    @Column(name = "shelter_pet_breed")
    private String petBreed;

    @Column(name = "shelter_pet_color")
    private String petColor;

    @Column(name = "shelter_pet_name")
    private String petName;

    @Column(name = "shelter_pet_age")
    private int petAge;

    //Date when pet registered in shelter
    @Column(name = "shelter_pet_reciept_date")
    private LocalDateTime petRecieptDate;

    /**
     * Describes which user is attached to
     * @see User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_user_id")
    @JsonBackReference
    private User user;

    /**
     * Constructor - creates an instance of Pet class with parameters
     * @param petKind
     * @param petBreed
     * @param petColor
     * @param petName
     * @param petAge
     * @param petRecieptDate
     */
    public Pet(String petKind, String petBreed, String petColor, String petName, int petAge, LocalDateTime petRecieptDate) {
        this.petKind = petKind;
        this.petBreed = petBreed;
        this.petColor = petColor;
        this.petName = petName;
        this.petAge = petAge;
        this.petRecieptDate = petRecieptDate;
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

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
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

    public LocalDateTime getPetRecieptDate() {
        return petRecieptDate;
    }

    public void setPetRecieptDate(LocalDateTime petRecieptDate) {
        this.petRecieptDate = petRecieptDate;
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
