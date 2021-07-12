package com.sample.demo.repository;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectResources;
import com.sample.demo.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResources,Integer> {
    List<ProjectResources> findByProject(Project project);

    List<ProjectResources> findByProjectAndResource(Project project, Resource resource);
}
