package com.alissonfgc.casacerta.entities.roles;

public enum UserRole {

    ADMIN("admin"),
    SELLER("seller"),
    CLIENT("client");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
