package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectResources;
import com.sample.demo.Entity.Resource;
import com.sample.demo.Service.ProjectResourceService;
import com.sample.demo.repository.ProjectRepository;
import com.sample.demo.repository.ProjectResourceRepository;
import com.sample.demo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectResourceServiceImpl implements ProjectResourceService {

    @Autowired
    ProjectResourceRepository projectResourceRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    @Transactional
    public boolean create(ProjectResources projectResource, Project project, Resource resource) {
        if(projectResource==null || project==null || resource==null)
            return false;

        try{
            projectResourceRepository.save(projectResource);
            project.addProjectResources(projectResource);
            resource.addProjects(projectResource);
            projectRepository.save(project);
            resourceRepository.save(resource);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(ProjectResources projectResource) {
        if(projectResource==null){
            return false;
        }
        System.out.println("deleting ptr: " +projectResource.getRecordId());
        try{
            projectResourceRepository.delete(projectResource);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public ProjectResources get(Integer id) {
        Optional<ProjectResources> target = projectResourceRepository.findById(id);
        if(target.isPresent()){
            return target.get();
        }
        return null;
    }

    @Override
    public ProjectResources get(Project project, Resource resource) {
        List<ProjectResources> current = projectResourceRepository.findByProjectAndResource(project,resource);
        if(!current.isEmpty()){
            return current.get(-1); //return what?
        }
        return null;
    }

    @Override
    public List<ProjectResources> get(Project project) {

        return projectResourceRepository.findByProject(project);
    }

    @Override
    public void clear() {
        projectResourceRepository.deleteAll();
    }


//    @Override
//    public ProjectResources addProjectResource(ProjectResources pr) {
//        return repository.save(pr);
//    }
//
//    @Override
//    public void deleteResourceById(Integer id) {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public List<ProjectResources> getAllProjectResource() {
//        return repository.findAll();
//    }

}
