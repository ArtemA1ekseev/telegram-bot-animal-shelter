package com.skypro.telegrambotanimalshelter.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Class Volunteer describes shelter volunteers, who need to help the user with an adoption
 * <br>
 * Properties: <b>volunteerId</b>, <b>volunteerChatId</b>, <b>volunteerName</b>, <b>volunteerTrackingUsers</b>
 * @see User
 * @version 0.0.1
 * @author Bulat Bazarov
 */
@Entity
@Table(name = "shelter_volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "shelter_volunteer_id")
    private Long volunteerId;

    @Column(name = "chatId")
    private Long volunteerChatId;

    @Column(name = "shelter_volunteer_name")
    private String volunteerName;

    /**
     * Describes users which volunteer follows
     * @see User
     */
    @OneToMany
    Collection<User> volunteerTrackingUsers;

    /**
     * Constructor - creates an instance of Volunteer class with parameters
     * @param volunteerChatId
     * @param volunteerName
     * @param volunteerTrackingUsers
     */
    public Volunteer(Long volunteerChatId, String volunteerName, Collection<User> volunteerTrackingUsers) {
        this.volunteerChatId = volunteerChatId;
        this.volunteerName = volunteerName;
        this.volunteerTrackingUsers = volunteerTrackingUsers;
    }

    /**
     * Constructor - creates an instance of Volunteer class without parameters
     */
    public Volunteer() {
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public Collection<User> getVolunteerTrackingUsers() {
        return volunteerTrackingUsers;
    }

    public Long getVolunteerChatId() {
        return volunteerChatId;
    }

    public void setVolunteerChatId(Long volunteerChatId) {
        this.volunteerChatId = volunteerChatId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    @Override
    public String toString() {
        return "ShelterVolunteer{" +
                "volunteerId=" + volunteerId +
                ", volunteerChatId=" + volunteerChatId +
                ", volunteerName='" + volunteerName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Volunteer that = (Volunteer) o;
        if (hashCode() != that.hashCode()) return false;
        if (!Objects.equals(volunteerId, that.volunteerId)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(volunteerChatId, that.volunteerChatId) && Objects.equals(volunteerName, that.volunteerName) && Objects.equals(volunteerTrackingUsers, that.volunteerTrackingUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volunteerId, volunteerChatId, volunteerName, volunteerTrackingUsers);
    }
}
