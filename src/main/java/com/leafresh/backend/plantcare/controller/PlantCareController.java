package com.leafresh.backend.plantcare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leafresh.backend.plantcare.model.PlantCareDTO;
import com.leafresh.backend.plantcare.model.PlantCareEntity;
import com.leafresh.backend.plantcare.service.PlantCareService;

@RestController
@RequestMapping("/garden-diary/plant-care")
public class PlantCareController {


	private PlantCareService plantCareService;

	@Autowired
	public PlantCareController(PlantCareService plantCareService) {
		this.plantCareService = plantCareService;
	}

	// plantCare 를 저장하거나 update 하기

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> saveOrUpdatePlantCare(@RequestBody PlantCareDTO plantCareDTO) {

		plantCareService.saveOrUpdatePlantCare(plantCareDTO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "저장완료요~");
		response.put("data", plantCareDTO);
		return ResponseEntity.ok(response);
	}


	// userId별로 이벤트를 반환하도록 수정
	@GetMapping("/events")
	public ResponseEntity<Map<String, Object>> getPlantCareEventsByUserId(@RequestParam Integer userId) {
		// userId로 필터링해서 이벤트 가져오기
		List<PlantCareEntity> events = plantCareService.getEventsByUserId(userId);

		Map<String, Object> response = new HashMap<>();
		response.put("data", events);

		return ResponseEntity.ok(response);
	}






}
