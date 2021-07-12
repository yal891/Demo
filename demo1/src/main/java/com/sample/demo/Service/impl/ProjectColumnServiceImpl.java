package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectColumns;
import com.sample.demo.Service.ProjectColumnService;
import com.sample.demo.Service.ResourceDetailService;
import com.sample.demo.repository.ProjectColumnRepository;
import com.sample.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectColumnServiceImpl implements ProjectColumnService{

    @Autowired
    ProjectColumnRepository columnsRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceDetailService resourceDetailService;

    @Override
    public boolean create(ProjectColumns column) {

        if(column == null){
            return false;
        }
        try{
            columnsRepository.save(column);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean create(ProjectColumns column, Project project) {
        if (column == null || project == null) {
            return false;
        }
        try {
            columnsRepository.save(column);
            project.addColumns(column);
            projectRepository.save(project);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(ProjectColumns column) {
        if(column==null){
            return false;
        }
        System.out.println("deleting project column: " +column.getColumn_id());
        try{
            columnsRepository.delete(column);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ProjectColumns column, String newName) {
        ProjectColumns toUpdate = get(column.getColumn_id());
        if(toUpdate==null){
            return false;
        }
        column.setColumn_name(newName);
        try{
            columnsRepository.save(column);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ProjectColumns get(Integer id) {
        if(id ==null)
            return null;
        Optional<ProjectColumns> toGet = columnsRepository.findById(id);
        if(toGet.isPresent()){
            return toGet.get();
        }
        return null;
    }

    @Override
    public ProjectColumns get(Project project, String columnName) {

        List<ProjectColumns> toGet = columnsRepository.findByProjectAndColumnName(project,columnName);

        if(!toGet.isEmpty()){
            return toGet.get(-1);
        }
        return null;
    }


    @Override
    public String getColumnsJson(Project project) {
        List<String> allJson = new ArrayList<String>();

        List<ProjectColumns> columns = columnsRepository.findByProject(project);

        for(ProjectColumns column:columns){
            allJson.add( String.format("The project column is ,%d", + column.getColumn_id()));
        }
        return String.join(",",allJson);
    }

    @Override
    public void clear() {
        columnsRepository.deleteAll();
    }
//
//    @Override
//    public ProjectColumns addNewColumn(ProjectColumns projectColumn) {
//        return repository.save(projectColumn);
//    }
//
//    @Override
//    public void DeleteColumn(Integer id) {
//        repository.deleteById(id);
//    }
}
