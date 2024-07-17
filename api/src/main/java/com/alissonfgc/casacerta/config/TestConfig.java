package com.alissonfgc.casacerta.config;

import com.alissonfgc.casacerta.entities.User;
import com.alissonfgc.casacerta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User(null, "Jao", "jao@gmail.com", "(61) 9 8181-8181", "999.999.999.99", "12345678");

        userRepository.save(user);
    }
}
