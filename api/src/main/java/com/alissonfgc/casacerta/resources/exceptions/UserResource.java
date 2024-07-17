package com.alissonfgc.casacerta.resources.exceptions;

import com.alissonfgc.casacerta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

//    @GetMapping
//    public ResponseEntity<List<User>> findAll() {
//        List<User> list = service.findAll();
//        return ResponseEntity.ok().body(list);
//    }
}
