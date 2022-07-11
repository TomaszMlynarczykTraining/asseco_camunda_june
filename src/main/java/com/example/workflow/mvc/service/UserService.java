package com.example.workflow.mvc.service;

import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
