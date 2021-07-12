package com.sample.demo.Service;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.User;

import java.math.BigInteger;
import java.util.List;


public interface ProjectService {

    public boolean create(Project project, User user);
    public boolean delete(Project project);
    public Project get(Integer id);
    public void clear();
    Project saveProject(Project project);

    public String toJson(Integer id);

//    public Project getProjectById(Integer id);
//
//    public Project getProjectByCode(BigInteger code);
//
//    public Project updateProject(Project project);
//
//    public void deleteProjectById(Integer id);
//
//    public Project createProject(Project project);
//
//    public List<Project> findAll();
//
//    public List<Project> getProjectsByUserId(Integer id);
}
