package com.leafresh.backend.oauth.service;

import com.leafresh.backend.oauth.model.User;
import com.leafresh.backend.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByUserNickname(String userNickname) {
        return userRepository.findByUserNickname(userNickname);
    }

    @Override
    public Optional<User> findByUserMailAdress(String userMailAdress) {
        return userRepository.findByUserMailAdress(userMailAdress);
    }

    @Override
    public Boolean existsByUserMailAdress(String userMailAdress) {
        return userRepository.existsByUserMailAdress(userMailAdress);
    }

    @Override
    public Boolean existsByUserNickname(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }
}
