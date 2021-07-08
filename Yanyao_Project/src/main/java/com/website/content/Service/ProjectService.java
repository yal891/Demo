package com.website.content.Service;
import com.website.content.entity.Project;
import com.website.content.entity.Resource;
import com.website.content.entity.ResourceDetail;


import java.util.Date;
import java.util.List;

public interface ProjectService {
    public Project findOneById( Integer id);

    List<Project> findAll();

    public Project save (Project project);

    public void deleteById(Integer Id);

    public List<Project> findALLByUserId(Integer id);


    public Project findOneByProjectName(String name);


}