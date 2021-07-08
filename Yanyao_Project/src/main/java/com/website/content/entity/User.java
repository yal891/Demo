package com.website.content.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table (name = "user", uniqueConstraints = {@UniqueConstraint (columnNames = "user_name")})
@EntityListeners(AuditingEntityListener.class)
public class User {
    public enum Role {
        ADMIN, USER;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int userId;

    @Column (name = "user_name")
    private String username;

    @Column (name = "password")
    private String password;

    @Enumerated (EnumType.STRING)
    @Column (name = "role")
    private Role role;

    @CreatedDate
    @Column (name = "time_created",updatable = false, nullable = false)
    private Date time_created;

    @LastModifiedDate
    @Column (name = "time_updated",nullable = false)
    private Date time_updated;


    @Column (name = "title")
    private String title;


    @OneToMany(mappedBy = "user")
    private Set<Project> UserToProjectSet = new HashSet<>();

    //Constructors
    public User() {
    }

    public User(String username, String password, Date time_created, Date time_updated, String title) {
        this.username = username;
        this.password = password;
        this.time_created = time_created;
        this.time_updated = time_updated;
        this.title = title;

    }

    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Date getTime_created() {
        return time_created;
    }
    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }
    public Date getTime_updated() {
        return time_updated;
    }
    public void setTime_updated(Date time_updated) {
        this.time_updated = time_updated;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Project> getUserToProjectSet() {
        return UserToProjectSet;
    }



    public void setUserToProjectSet(Set<Project> userToProjectSet) {
        UserToProjectSet = userToProjectSet;
    }

}
