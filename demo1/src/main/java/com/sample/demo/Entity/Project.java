package com.sample.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = User.class)
    private User owner;


    private Timestamp time_created;

    private BigInteger projectCode;

    private String projectName;

    @OneToMany (mappedBy="project", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ProjectResources> projectResources;


    @JsonIgnore
    @OneToMany(targetEntity = ProjectColumns.class,cascade = CascadeType.REMOVE,mappedBy = "project")
    private Collection<ProjectColumns> columns = new HashSet<ProjectColumns>();


    public void addProjectResources(ProjectResources tmp){
        if(projectResources.contains(tmp)){
            return;
        }
        projectResources.add(tmp);
        tmp.setProject(this);
    }

    public Project() {
    }

    public Project(User owner, String projectName) {
        this.owner = owner;
        this.projectName = projectName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Set<ProjectResources> getProjectResources() {
        return projectResources;
    }

    public void setProjectResources(Set<ProjectResources> projectResources) {
        this.projectResources = projectResources;
    }

    public Timestamp getTime_created() {
        return time_created;
    }

    public void setTime_created(Timestamp time_created) {
        this.time_created = time_created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigInteger getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(BigInteger projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", user=" + //user +
                ", time_created=" + time_created +
                ", projectCode=" + projectCode +
                ", projectName='" + projectName + '\'' +
                ", projectResources=" + projectResources +
                '}';
    }

    public void addColumns(ProjectColumns column) {
        if (columns.contains(column)){
            return;
        }
        columns.add(column);
        column.setProject(this);

    }


//    public User getOwner() {
//        return user;
//    }
//    public void setOnwer(){
//        this.owner = owner;
//    }
}
