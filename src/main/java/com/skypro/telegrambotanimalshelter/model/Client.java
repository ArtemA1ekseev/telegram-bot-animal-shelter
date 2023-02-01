package com.skypro.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Class of potential adopter with properties <b>id</b>, <b>firstName</b>, <b>lastName</b>, <b>phoneNumber</b>,
 * <b>age</b>, <b>documentsProvided</b>
 * @version 0.0.1
 * @author i.gatin
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    private String phoneNumber;

    private int age;

    /**
     * Can be used to issue permission for "adoption" or use as a marker whether the trial period has passed
     */
    private Boolean documentsProvided;

    /**
     * A set of reports which client have to provide daile while in trial period
     * @see Report
      */
    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private Set<Report> report;

    /**
     * A set of pets which client has adopted
     * @see Pet
      */
    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private Set<Pet> pet;

    /**
     * Constructor - creates new instance of Client class whith parameters
      * @param id
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param age
     * @param documentsProvided
     */
    public Client(Long id, String firstName, String lastName, String phoneNumber, int age, Boolean documentsProvided) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.documentsProvided = false;

    }

    /**
     * Constructor - creates an instance of Client class
      */
    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getDocumentsProvided() {
        return documentsProvided;
    }

    public void setDocumentsProvided(Boolean documentsProvided) {
        this.documentsProvided = documentsProvided;
    }

    public Set<Report> getReport() {
        return report;
    }

    public void setReport(Set<Report> report) {
        this.report = report;
    }

    public Set<Pet> getPet() {
        return pet;
    }

    public void setPet(Set<Pet> pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

