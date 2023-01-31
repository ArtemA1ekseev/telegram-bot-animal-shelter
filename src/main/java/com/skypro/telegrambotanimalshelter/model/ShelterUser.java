package com.skypro.telegrambotanimalshelter.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "shelter_user")
public class ShelterUser {

    @Id
    @GeneratedValue
    @Column(name = "shelter_user_id")
    private Long userId;

    @Column(name = "shelter_user_chat_id")
    private Long userChatId

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

    @OneToMany
    private Collection<Pet> userAdoptedPets;

    public ShelterUser() {
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

    public String getUserPhane() {
        return userPhone;
    }

    public void setUserPhane(String userPhane) {
        this.userPhone = userPhane;
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
        ShelterUser that = (ShelterUser) o;
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
