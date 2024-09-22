package com.leafresh.backend.plant.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "plants")
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plant_name")
    private String plantName;

    @Column(name = "plant_type")
    private String plantType;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "plant_description")
    private String plantDescription;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "user_Id")
    private Integer userID;

    @Column(name = "user_nickname")
    private String userNickName;

    public PlantEntity() {
    }

    public PlantEntity(Long id, String plantName, String plantType, LocalDate registrationDate, String plantDescription,
        String imageUrl, Integer userID, String userNickName) {
        this.id = id;
        this.plantName = plantName;
        this.plantType = plantType;
        this.registrationDate = registrationDate;
        this.plantDescription = plantDescription;
        this.imageUrl = imageUrl;
        this.userID = userID;
        this.userNickName = userNickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    @Override
    public String toString() {
        return "PlantEntity{" +
            "id=" + id +
            ", plantName='" + plantName + '\'' +
            ", plantType='" + plantType + '\'' +
            ", registrationDate=" + registrationDate +
            ", plantDescription='" + plantDescription + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", userID=" + userID +
            ", userNickName='" + userNickName + '\'' +
            '}';
    }
}