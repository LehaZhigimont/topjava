package ru.javawebinar.topjava.model;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");
    private String role;

    Role(String title) {
        this.role = title;
    }

    public String getRole() {
        return role;
    }
}