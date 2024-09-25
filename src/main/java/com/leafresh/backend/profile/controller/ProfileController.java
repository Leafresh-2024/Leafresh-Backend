package com.leafresh.backend.profile.controller;

import com.leafresh.backend.common.codes.SuccessCode;
import com.leafresh.backend.oauth.model.User;
import com.leafresh.backend.oauth.repository.UserRepository;
import com.leafresh.backend.oauth.security.CurrentUser;
import com.leafresh.backend.oauth.security.UserPrincipal;
import com.leafresh.backend.profile.model.dto.ProfileDTO;
import com.leafresh.backend.profile.model.entity.ProfileEntity;
import com.leafresh.backend.profile.repository.ProfileRepository;
import com.leafresh.backend.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    private final ProfileService profileService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileController(ProfileService profileService, UserRepository userRepository, ProfileRepository profileRepository) {
        this.profileService = profileService;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProfile(@CurrentUser UserPrincipal userPrincipal, @RequestBody ProfileDTO profileDTO) {
        if (profileDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ProfileDTO savedDTO = profileService.createProfile(userPrincipal.getUserId(), profileDTO);

        if (savedDTO != null) { // 프로필이 잘 저장되었으면
            return ResponseEntity.status(SuccessCode.PROFILE_CREATED.getStatus()).body(savedDTO);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getProfileInfo(@CurrentUser UserPrincipal userPrincipal) {
        try {
            ProfileDTO profileDTO = profileService.getProfileInfo(userPrincipal.getUserId());
            return ResponseEntity.status(SuccessCode.OK.getStatus()).body(profileDTO); // 프로필 정보 반환
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("정보가 없습니다."); // 프로필이 없는 경우 에러 메시지 반환
        }
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyProfile(@CurrentUser UserPrincipal userPrincipal, @RequestBody ProfileDTO profileDTO) {
        if (profileDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ProfileDTO modifyDTO = profileService.modifyProfile(userPrincipal.getUserId(), profileDTO);

        if (modifyDTO != null) {
            return ResponseEntity.status(SuccessCode.PROFILE_UPDATED.getStatus()).body(modifyDTO);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/info-by-nickname")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProfileDTO> getProfileByUserNickname(@RequestParam("nickname") String userNickname) {
        if (userNickname == null || userNickname.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ProfileDTO profileDTO = profileService.findProfileByUserNickname(userNickname);

        if (profileDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(SuccessCode.OK.getStatus()).body(profileDTO);
    }
}
