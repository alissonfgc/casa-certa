package com.alissonfgc.casacerta.entities;

import com.alissonfgc.casacerta.entities.roles.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "tb_admin")
@Entity(name = "admin")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String id, String name, String email, String phoneNumber, String registrationNumber, String password) {
        super(id, name, email, phoneNumber, registrationNumber, password);
        this.role = UserRole.ADMIN;
    }
}
