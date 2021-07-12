package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.User;
import com.sample.demo.Service.ProjectColumnService;
import com.sample.demo.Service.ProjectResourceService;
import com.sample.demo.Service.ProjectService;
import com.sample.demo.Service.ResourceService;
import com.sample.demo.repository.ProjectRepository;
import com.sample.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectResourceService projectToResourceService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ProjectColumnService columnsService;


    @Override
    public Project saveProject(Project project) {
       return projectRepository.save(project);
    }

    @Override
    public boolean create(Project project, User user) {
        if(project==null || user ==null) {
            return false;
        }
        try{
            projectRepository.save(project);
            user.addProject(project);
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return  true;
    }


    @Override
    @Transactional
    public boolean delete(Project project) {
        if(project==null){
            return false;
        }
        System.out.println("deleting project: " +project.getProject_id());
        try{
            projectRepository.delete(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return  true;
    }


    @Override
    public Project get(Integer id) {
        if(id==null)
            return null;
        Optional<Project> toGet = projectRepository.findById(id);
        if(toGet.isPresent()){
            return toGet.get();
        }
        return null;
    }

    @Override
    public void clear() {
        projectRepository.deleteAll();
    }

    @Override
    public String toJson(Integer id) {

        Project project = get(id);
        if(id==null||project==null)
            return null;
        String s =  String.format("The project is ,%d",project.getProject_id());

        return s;
    }




//    @Override
//    public Project getProjectById(Integer id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    @Override
//    public Project getProjectByCode(BigInteger code){
//        return repository.findByProjectCode(code);
//    }
//
//    @Override
//    public Project updateProject(Project project) {
//        return repository.save(project);
//    }
//
//    @Override
//    public void deleteProjectById(Integer id) {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public Project createProject(Project project) {
//        return repository.saveAndFlush(project);
//    }
//
//    @Override
//    public List<Project> findAll() {
//        return repository.findAll();
//    }
//
//    @Override
//    public List<Project> getProjectsByUserId(Integer id) {
//        List<Project> projects = repository.findAll();
//        List<Project> res = new ArrayList<>();
//        for(Project project : projects){
//            if(project.getUser().getId() == id){
//                res.add(project);
//            }
//        }
//        return res;
//    }
}
