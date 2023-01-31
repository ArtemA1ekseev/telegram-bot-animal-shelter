package com.skypro.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    private String phoneNumber;

    private int age;

    /*
    Можно по этому флагу выдавать разрешение на "усыновление"
     или использовать как маркер пройден ли испытательный период
     */
    private Boolean documentsProvided;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private Set<Report> report;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private Set<Pet> pet;

    public Client(Long id, String firstName, String lastName, String phoneNumber, int age, Boolean documentsProvided) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.documentsProvided = false;

    }

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

