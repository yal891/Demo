package com.website.content.Service.imp;

import com.website.content.Service.ProjectService;
import com.website.content.entity.Project;
import com.website.content.entity.Resource;
import com.website.content.entity.ResourceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.website.content.DAO.ProjectDAO;


import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectDAO repository;

    @Override
    public Project findOneById(Integer Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }
    @Override
    public Project save(Project project){
        return repository.save(project);
    }

    @Override
    public void deleteById(Integer Id){
        repository.deleteById(Id);
    }

    @Override
    public List<Project> findALLByUserId(Integer Id) {
        return repository.findALLByUserId(Id);
    }

  
      @Override
    public Project findOneByProjectName( String name ) {
        return repository.findByProject_name(name);
    }


}