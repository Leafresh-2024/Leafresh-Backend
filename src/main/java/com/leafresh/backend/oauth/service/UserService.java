package com.leafresh.backend.oauth.service;

import com.leafresh.backend.oauth.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer userId); // userId로 유저 정보 가져오기
    Optional<User> findByUserNickname(String userNickname); // userNickname으로 유저 정보 가져오기
}
