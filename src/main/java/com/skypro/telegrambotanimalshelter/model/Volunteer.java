package com.skypro.telegrambotanimalshelter.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

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

    @OneToMany
    Collection<User> volunteerTrackingUsers;

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
