package com.website.content.Service.imp;

import com.website.content.Service.ProjectService;
import com.website.content.Service.ProjectToResourceService;
import com.website.content.DAO.ProjectToResourceDAO;
import com.website.content.Service.ResourceService;
import com.website.content.entity.Project;
import com.website.content.entity.ProjectToResource;
import com.website.content.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectToResourceServiceImp implements ProjectToResourceService {

    @Autowired
    ProjectToResourceDAO repository;

    @Autowired
    ResourceService resourceService;

    @Qualifier ("projectServiceImp")
    @Autowired
    ProjectService projectService;

    @Override
    public ProjectToResource addResourceToProject(Project project, Resource resource) {
        ProjectToResource projectToResource = new ProjectToResource();
        projectToResource.setProject(project);
        projectToResource.setResource(resource);
        repository.save(projectToResource);
        return projectToResource;
    }

    @Override
    public List<ProjectToResource> findAll() {
        return repository.findAll();
    }

    @Override
    public ProjectToResource findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProjectToResource updateResource(Integer projectResourceId, Integer resourceId) {
        ProjectToResource projectToResource = findById(projectResourceId);
        Resource resource = resourceService.findOneById(resourceId);
        projectToResource.setResource(resource);
        return repository.save(projectToResource);
    }

    @Override
    public void deleteProjectResource(Integer id) {
        repository.deleteById(id);
    }

}

