package com.sample.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "user_name")
    private String username;


    @Enumerated(value = EnumType.STRING)
    private Role role;


    @CreatedDate
    @Column(name = "time_created")
    private Date timeCreated;


    @LastModifiedDate
    @Column(name = "last_updated")
    private Date lastUpdated;


    private String password;


    @OneToMany(mappedBy = "user",
            fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Project> project;


    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    public void addProject(Project newProject){
        if (newProject != null){
            project.add(newProject);
        }
    }


    public User() {}


    public User(String user_name, Role role, String password) {
        this.username = user_name;
        this.role = role;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }


    public Role getRole() {
        return role;
    }


    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public Date getTime_created() {
        return timeCreated;
    }

    public Date getLast_updated() {
        return lastUpdated;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setTime_created(Date time_created) {
        this.timeCreated = time_created;
    }

    public void setLast_updated(Date last_updated) {
        this.lastUpdated = last_updated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", time_created=" + timeCreated +
                ", last_updated=" + lastUpdated +
                '}';
    }
}