package com.alissonfgc.casacerta.services;

import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
