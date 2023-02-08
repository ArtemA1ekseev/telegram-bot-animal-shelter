package com.skypro.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.Objects;

/**
 * Class of owner for the dog.
 * @author Artem Alekseev
 * @version 1.0.0
 */
@Entity
public class PersonDog {

    /** "ID" field */
    @Id
    @GeneratedValue
    private Long id;

    /** "Name" field */
    private String name;

    /** "Year Of Birth" field */
    private int yearOfBirth;

    /** "Phone" field */
    private String phone;

    /** "Mail" field */
    private String mail;

    /** "Address" field */
    private String address;

    /** "id Chat" field */
    private Long chatId;

    private Status status;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "person_report_data",
            joinColumns = @JoinColumn(name = "person_null"),
            inverseJoinColumns = @JoinColumn(name = "report_data_id"))
    private ReportData reportData;

    /**
     * Constructor - creating a new object.
     * @see PersonDog
     */
    public PersonDog() {
    }

    /**
     * Constructor - creating a new object with certain values.
     * @param name
     * @param phone
     * @param chatId
     */
    public PersonDog(String name, String phone, Long chatId) {
        this.name = name;
        this.phone = phone;
        this.chatId = chatId;
    }

    /**
     * Constructor - creating a new object with certain values.
     * @param id
     * @param name
     * @param yearOfBirth
     * @param phone
     * @param mail
     * @param address
     * @param chatId
     */
    public PersonDog(Long id, String name, int yearOfBirth, String phone, String mail, String address, Long chatId) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.chatId = chatId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getChatId() {
        return chatId;
    }

    public ReportData getReportData() {
        return reportData;
    }

    public void setReportData(ReportData reportData) {
        this.reportData = reportData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDog personDog = (PersonDog) o;
        return id == personDog.id && yearOfBirth == personDog.yearOfBirth && phone == personDog.phone && Objects.equals(name, personDog.name) && Objects.equals(mail, personDog.mail) && Objects.equals(address, personDog.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfBirth, phone, mail, address);
    }

    @Override
    public String toString() {
        return "PersonDog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", chatId=" + chatId +
                ", status=" + status +
                ", dog=" + dog +
                ", reportData=" + reportData +
                '}';
    }
}