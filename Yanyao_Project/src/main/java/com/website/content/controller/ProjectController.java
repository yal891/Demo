package com.website.content.controller;

import com.website.content.Service.ProjectService;
import com.website.content.entity.Project;
import com.website.content.entity.ResourceDetail;
import com.website.content.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Qualifier ("projectServiceImp")
    @Autowired
    private ProjectService service;

    @GetMapping("/projects")
    public List<Project> projectsList(){
        return service.findAll();
    }

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable("id") Integer id){
        return service.findOneById(id);
    }

    @PostMapping("/projects")
    public Project create(@RequestBody Project project){

        return service.save(project);
    }

//    @PutMapping("/projects/{id}")
//    public Project updateProject(@PathVariable("id") Integer id, @RequestParam("name") String name){
//        Project project = service.findOneById(id);
//        project.setProject_name(name);
//        return service.save(project);
//    }

    @PutMapping("/updateProjects")
    public Project updateProject(@RequestBody Project project){
        return service.save(project);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable("id") Integer id){
        Project project = service.findOneById(id);
        service.deleteById(id);
    }

    @GetMapping("/findProjectByUserId")
    public List<Project> list(@RequestParam("user_id") Integer id) {
        return service.findALLByUserId(id);
    }
}