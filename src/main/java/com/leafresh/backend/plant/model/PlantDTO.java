package com.leafresh.backend.plant.model;

import java.time.LocalDate;
import java.util.Arrays;

public class PlantDTO {

    private Long id;
    private String plantName;
    private String plantType;
    private LocalDate registrationDate;
    private String plantDescription;
    private String imageUrl;
    private Integer userId;
    private String userNickname;


    public PlantDTO() {
    }

    public PlantDTO(Long id, String plantName, String plantType, LocalDate registrationDate, String plantDescription,
        String imageUrl, Integer userId, String userNickname) {
        this.id = id;
        this.plantName = plantName;
        this.plantType = plantType;
        this.registrationDate = registrationDate;
        this.plantDescription = plantDescription;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.userNickname = userNickname;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Override
    public String toString() {
        return "PlantDTO{" +
            "id=" + id +
            ", plantName='" + plantName + '\'' +
            ", plantType='" + plantType + '\'' +
            ", registrationDate=" + registrationDate +
            ", plantDescription='" + plantDescription + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", userId=" + userId +
            ", userNickname='" + userNickname + '\'' +
            '}';
    }
}