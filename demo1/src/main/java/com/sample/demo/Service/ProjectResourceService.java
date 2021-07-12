package com.sample.demo.Service;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectResources;
import com.sample.demo.Entity.Resource;

import java.util.List;

public interface ProjectResourceService {
    public boolean create(ProjectResources projectToResource, Project project, Resource resource);

    public boolean delete(ProjectResources projectToResource);

    public void clear();

    public ProjectResources get(Integer id);

    public ProjectResources get(Project project, Resource resource);

    public List<ProjectResources> get(Project project);



//    public ProjectResources addProjectResource(ProjectResources pr);
//
//    public void deleteResourceById(Integer id);
//
//    public List<ProjectResources> getAllProjectResource();
//
//    //public List<ResourcesDetails>  getResourceByProjects(List<Project> projects);
}
