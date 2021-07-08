package com.website.content.Service;

import com.website.content.entity.Project;
import com.website.content.entity.ProjectToResource;
import com.website.content.entity.Resource;

import java.util.List;

public interface ProjectToResourceService {
    public ProjectToResource addResourceToProject(Project project, Resource resource);

    public List<ProjectToResource> findAll();

    public ProjectToResource findById(Integer id);
    public ProjectToResource updateResource(Integer projectResourceId, Integer resourceId);
    public void deleteProjectResource(Integer id);

}
