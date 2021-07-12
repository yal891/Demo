package com.sample.demo.Service;

import com.sample.demo.Entity.Project;
import com.sample.demo.Entity.Resource;

import java.util.List;

public interface ResourceService {
//    public Resource getResourceById(Integer id);
//
//    public Resource createResource(Resource resource);
//
//    public List<Resource> getAllResource();
//
//    public void deleteResourceById(Integer id);

    Resource saveResource(Resource resource);

    //check create
    public boolean create(Resource resource);

    //check delete
    public boolean delete(Resource resource);

    //check update
    public boolean up(Resource resource);

    //check read
    public boolean re(Resource resource);
    public Resource get(Integer id);
    public String getAllJson();
    public void clear();
}
