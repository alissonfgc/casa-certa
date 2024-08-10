package com.alissonfgc.casacerta.entities;

import com.alissonfgc.casacerta.entities.roles.UserRole;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

//, UserDetails

@Table(name = "tb_user")
@Entity(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;
    @Column(nullable = false)
    protected String name;
    @Column(unique = true, nullable = false)
    protected String email;
    @Column(name = "PHONE_NUMBER", nullable = false)
    protected String phoneNumber;
    @Column(name = "CPF_CNPJ", nullable = false)
    protected String registrationNumber;
    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected UserRole role;


    public User() {
    }

    public User(String id, String name, String email, String phoneNumber, String registrationNumber, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationNumber = registrationNumber;
        this.password = password;
        this.role = UserRole.CLIENT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}