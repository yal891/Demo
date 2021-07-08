package com.website.content.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int project_id;

    @Column(name = "project_name")
    private String project_name;

    @CreatedDate
    @Column (name = "time_created",updatable = false, nullable = false)
    private Date time_created;

    @LastModifiedDate
    @Column (name = "time_updated",nullable = false)
    private Date time_updated;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="project",
            cascade = CascadeType.ALL)
    private Set<ProjectToResource> projectToResources = new HashSet<>();

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="project",
            cascade = CascadeType.ALL)
    private Set<ResourceDetail> resourceDetails = new HashSet<>();

    public Project() {
    }

    public Project( String project_name, Date time_created, Date time_updated) {
        this.project_name = project_name;
        this.time_created = time_created;
        this.time_updated = time_updated;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public User getUser() {return user; }

    public void setUser(User user) {this.user = user;}

    public Set<ProjectToResource> getProjectToResources() {return projectToResources;}

    public void setProjectToResources(Set<ProjectToResource> projectToResources) {this.projectToResources = projectToResources; }

    public Set<ResourceDetail> getResourceColumns() {return resourceDetails; }

    public void setResourceColumns(Set<ResourceDetail> resourceDetails) { this.resourceDetails = resourceDetails; }
}
