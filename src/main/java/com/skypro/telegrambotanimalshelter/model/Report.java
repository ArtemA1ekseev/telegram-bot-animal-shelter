package com.skypro.telegrambotanimalshelter.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/** Used for daily reports from clients
 * <br>
 * Properties: <b>id</b>, <b>reportDate</b>, <b>petDiet</b>, <b>description</b>
 * @see User
 * Properties: <b>id</b>, <b>reportDate</b>, <b>petDiet</b>, <b>description</b>, <b>photo</b>
 * @see User
 * @see Photo
 * @version 0.0.1
 * @author i.gatin
 */
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date reportDate;
    private String petDiet;
    private String description;
    private Photo photo;

    private String filePath;

    private long fileSize;

    private String mediaType;

    @Lob
    private byte[] data;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_pet_id")
    @JsonBackReference
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_user_id")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Report(Date reportDate, String petDiet, String description, Photo photo) {
        this.reportDate = reportDate;
        this.petDiet = petDiet;
        this.description = description;
        this.photo = photo;
    }

    public Report() {
    }
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getPetDiet() {
        return petDiet;
    }

    public void setPetDiet(String petDiet) {
        this.petDiet = petDiet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
