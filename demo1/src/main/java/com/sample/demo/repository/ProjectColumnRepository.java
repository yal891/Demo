package com.sample.demo.repository;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectColumns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectColumnRepository extends JpaRepository<ProjectColumns, Integer> {
    List<ProjectColumns> findByProjectAndColumnName(Project project, String columnName);


    List<ProjectColumns> findByProject(Project project);
}
