package com.skypro.telegrambotanimalshelter.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Class User describes shelter users, who can adopt a pet
 * <br>
 * Properties: <b>userId</b>, <b>userChatId</b>, <b>userName</b>, <b>userAge</b>, <b>userAddress</b>,
 * <b>userPhone</b>, <b>userAbleAdoptPet</b>, <b>userAdoptedPets</b>
 * @see Pet
 * @version 0.0.1
 * @author Bulat Bazarov
 */
@Entity
@Table(name = "shelter_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "shelter_user_id")
    private Long userId;

    @Column(name = "shelter_user_chat_id")
    private Long userChatId;

    @Column(name = "shelter_user_name")
    private String userName;

    @Column(name = "shelter_user_age")
    private int userAge;

    @Column(name = "shelter_user_address")
    private String userAddress;

    @Column(name = "shelter_user_phone")
    private String userPhone;

    @Column(name = "shelter_user_able_adopt_pet")
    private boolean userAbleToAdoptPet;

    /**
     * Describes pets adopted by user
     * @see Pet
     */
    @OneToMany
    private Collection<Pet> userAdoptedPets;

    /**
     * Constructor - creates an instance of User class with parameters
     * @param userChatId
     * @param userName
     * @param userAge
     * @param userAddress
     * @param userPhone
     * @param userAbleToAdoptPet
     */
    public User(Long userChatId, String userName, int userAge, String userAddress, String userPhone, boolean userAbleToAdoptPet) {
        this.userChatId = userChatId;
        this.userName = userName;
        this.userAge = userAge;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userAbleToAdoptPet = userAbleToAdoptPet;
    }

    /**
     * Constructor - creates an instance of User class without parameters
     */
    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public Long getUserChatId() {
        return userChatId;
    }

    public void setUserChatId(Long userChatId) {
        this.userChatId = userChatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isUserAbleToAdoptPet() {
        return userAbleToAdoptPet;
    }

    public void setUserAbleToAdoptPet(boolean userAbleToAdoptPet) {
        this.userAbleToAdoptPet = userAbleToAdoptPet;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhane) {
        this.userPhone = userPhane;
    }

    public Collection<Pet> getUserAdoptedPets() {
        return userAdoptedPets;
    }

    @Override
    public String toString() {
        return "ShelterUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        User that = (User) o;
        if (this.hashCode() != that.hashCode()) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(userChatId, that.userChatId) && userAge == that.userAge && Objects.equals(userName, that.userName) && Objects.equals(userAddress, that.userAddress) && Objects.equals(userPhone, that.userPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userAge, userAddress, userPhone);
    }
}
