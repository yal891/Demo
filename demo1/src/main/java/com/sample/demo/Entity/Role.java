package com.sample.demo.Entity;

public enum Role {
    User("User"),
    Admin("Admin");

    private String role;


    Role(String role) {
        this.role = role;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
