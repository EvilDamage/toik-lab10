package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public ResponseEntity<Void> checkLogin(String login, String password) {
        return this.userRepository.checkLogin(login, password);
    }

}
