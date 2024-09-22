package com.leafresh.backend.plant.service;


import com.leafresh.backend.plant.model.PlantDTO;
import com.leafresh.backend.plant.model.PlantEntity;
import com.leafresh.backend.plant.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public PlantDTO savePlant(PlantDTO plantDTO) throws IOException {
        // 로그 추가
        System.out.println("UserId: " + plantDTO.getUserId());
        System.out.println("UserNickname: " + plantDTO.getUserNickname());


        PlantEntity plantEntity = new PlantEntity();
        plantEntity.setUserID(plantDTO.getUserId());
        plantEntity.setUserNickName(plantDTO.getUserNickname());
        plantEntity.setPlantName(plantDTO.getPlantName());
        plantEntity.setPlantType(plantDTO.getPlantType());
        plantEntity.setRegistrationDate(plantDTO.getRegistrationDate());
        plantEntity.setPlantDescription(plantDTO.getPlantDescription());
        plantEntity.setImageUrl(plantDTO.getImageUrl());

        PlantEntity savedPlant = plantRepository.save(plantEntity);
        return convertToDTO(savedPlant);
    }

    public List<PlantDTO> getAllPlants() {
        return plantRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }


    // 유저 Nickname 별로 가져온다 식물정보를
    public List<PlantDTO> getPlantsByUserNickname(String userNickname) {
        List<PlantEntity> plantEntities = plantRepository.findByUserNickName(userNickname);
        return plantEntities.stream()
            .map(this::convertToDTO) // plantEntity를 plantDto 로 변환
            .toList();

    }

    public Optional<PlantDTO> getPlantById(Long id) {
        return plantRepository.findById(id).map(this::convertToDTO);
    }

    public void deletePlant(Long id) {
        plantRepository.deleteById(id);
    }

    public PlantDTO updatePlant(Long id, PlantDTO plantDTO) throws IOException {
        PlantEntity plantEntity = plantRepository.findById(id).orElseThrow();
        plantEntity.setPlantName(plantDTO.getPlantName());
        plantEntity.setPlantType(plantDTO.getPlantType());
        plantEntity.setRegistrationDate(plantDTO.getRegistrationDate());
        plantEntity.setPlantDescription(plantDTO.getPlantDescription());
        plantEntity.setImageUrl(plantDTO.getImageUrl());

        PlantEntity updatedPlant = plantRepository.save(plantEntity);
        return convertToDTO(updatedPlant);
    }

    private PlantDTO convertToDTO(PlantEntity plantEntity) {
        PlantDTO dto = new PlantDTO();
        dto.setUserId(plantEntity.getUserID());
        dto.setPlantName(plantEntity.getPlantName());
        dto.setId(plantEntity.getId());
        dto.setPlantName(plantEntity.getPlantName());
        dto.setPlantType(plantEntity.getPlantType());
        dto.setRegistrationDate(plantEntity.getRegistrationDate());
        dto.setPlantDescription(plantEntity.getPlantDescription());
        dto.setImageUrl(plantEntity.getImageUrl());
        return dto;
    }





}
