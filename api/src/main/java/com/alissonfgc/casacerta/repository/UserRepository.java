package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByEmail(String email);

    UserDetails findByEmail(String email);
}
