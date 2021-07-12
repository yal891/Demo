package com.sample.demo.Service;

import com.sample.demo.Entity.ProjectColumns;
import com.sample.demo.Entity.Resource;
import com.sample.demo.Entity.ResourcesDetails;

import java.util.List;

public interface ResourceDetailService {

    public boolean create(ResourcesDetails resourceDetails, Resource resource, ProjectColumns column) ;
    public boolean delete(ResourcesDetails resourceDetails);
    public boolean update(ResourcesDetails resourceDetails);


    public ResourcesDetails get(Integer id);

    public ResourcesDetails get(Resource resource, ProjectColumns column);


    public void clear();


//    public ResourcesDetails createDetails(ResourcesDetails details);
//
//    //public ResourcesDetails UpdateDetails(ResourcesDetails details);
//
//     public List<ResourcesDetails> getAllDetails();
//
//    public void deleteResourceDetailById(Integer id);
//
//    public ResourcesDetails getOneById(Integer id);


}
