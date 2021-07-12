package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.ProjectColumns;
import com.sample.demo.Entity.Resource;
import com.sample.demo.Entity.ResourcesDetails;
import com.sample.demo.Service.ProjectColumnService;
import com.sample.demo.Service.ProjectResourceService;
import com.sample.demo.Service.ResourceDetailService;
import com.sample.demo.Service.ResourceService;
import com.sample.demo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ProjectColumnService columnsService;
    @Autowired
    private ResourceDetailService resourceDetailsService;
    @Autowired
    private ProjectResourceService projectToResourceService;

    @Override
    public boolean create(Resource resource) {
        if(resource==null){
            return false;
        }
        try{
            resourceRepository.save(resource);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Resource saveResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    @Transactional
    public boolean delete(Resource resource) {
        if(resource==null){
            return false;
        }
        System.out.println("deleting resource: " +resource.getResource_id());
        try{
            resourceRepository.delete(resource);
        }catch(Exception e){
            System.out.println(e.getMessage());;
            return false;
        }
        return true;
    }

    @Override
    public boolean up(Resource resource) {
        return true;
    }

    @Override
    public boolean re(Resource resource) {
        return true;
    }


    @Override
    public Resource get(Integer id) {
        Optional<Resource> curResource = resourceRepository.findById(id);
        if(curResource.isPresent()){
            return curResource.get();
        }else{
            return null;
        }
    }


    @Override
    public String getAllJson() {
        List<Resource> resources = resourceRepository.findAll();

        List<String> allJson = new ArrayList<String>();
        for(Resource resource : resources){
            String s = String.format("The resource is ,%s", resource.getResource_id());
            allJson.add(s);
        }
        return String.join(",", allJson);
    }

    @Override
    public void clear() {
        resourceRepository.deleteAll();
    }



}
