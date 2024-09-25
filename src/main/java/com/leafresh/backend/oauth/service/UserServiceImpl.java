package com.leafresh.backend.oauth.service;

import com.leafresh.backend.oauth.model.User;

import java.util.Optional;

public interface UserServiceImpl {
    Optional<User> findById(Integer userId); // userId로 유저 정보 가져오기
    Optional<User> findByUserNickname(String userNickname); // userNickname으로 유저 정보 가져오기
    Optional<User> findByUserMailAdress(String userMailAdress);
    Boolean existsByUserMailAdress(String userMailAdress); // 이메일 주소 중복확인을 위한 메서드
    Boolean existsByUserNickname(String userNickname); // 닉네임 중복확인을 위한 메서드
}
