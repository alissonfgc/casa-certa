package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
