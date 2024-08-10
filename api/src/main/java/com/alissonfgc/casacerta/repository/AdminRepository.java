package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findByEmail(String email);
}
