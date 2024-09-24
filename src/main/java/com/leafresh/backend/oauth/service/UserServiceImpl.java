package com.leafresh.backend.oauth.service;

import com.leafresh.backend.oauth.model.User;
import com.leafresh.backend.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
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
}
