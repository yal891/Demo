package com.sample.demo.Service;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectColumns;

import java.util.List;

public interface ProjectColumnService {

    public boolean create(ProjectColumns column);
    public boolean create(ProjectColumns column,Project project);

    public boolean delete(ProjectColumns column);
    public boolean update(ProjectColumns column, String newColumnName);
    public void clear();

    public ProjectColumns get(Integer id);
    public ProjectColumns get(Project project, String columnName);

    public String getColumnsJson(Project project);


//    public ProjectColumns addNewColumn(ProjectColumns projectColumn);
//
//    public void DeleteColumn(Integer id);
}
