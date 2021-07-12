package com.sample.demo.Service.impl;

import com.sample.demo.Entity.ProjectColumns;
import com.sample.demo.Entity.Resource;
import com.sample.demo.Entity.ResourcesDetails;
import com.sample.demo.Service.ResourceDetailService;
import com.sample.demo.Service.ResourceService;
import com.sample.demo.repository.ProjectColumnRepository;
import com.sample.demo.repository.ResourceRepository;
import com.sample.demo.repository.ResourcesDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceDetailServiceImpl implements ResourceDetailService {

    @Autowired
    private ResourcesDetailRepository resourceDetailsRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ProjectColumnRepository columnsRepository;



    @Override
    public boolean create(ResourcesDetails resourceDetails, Resource resource, ProjectColumns column) {
        if(resourceDetails==null || resource==null || column==null)
            return false;

        try {
//            resource.addDetails(resourceDetails);?????
//            column.addDetails(resourceDetails);???
            resourceDetailsRepository.save(resourceDetails);
            columnsRepository.save(column);
            resourceDetailsRepository.save(resourceDetails);
            resourceRepository.save(resource);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    @Transactional
    public boolean delete(ResourcesDetails resourceDetails) {
        if(resourceDetails==null){
            return false;
        }
        System.out.println("deleting rd: " +resourceDetails.getRecordId());
        try {
            resourceDetailsRepository.delete(resourceDetails);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ResourcesDetails resourceDetails) {
        try {
            resourceDetailsRepository.save(resourceDetails);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }



    @Override
    public ResourcesDetails get(Integer id) {
        Optional<ResourcesDetails> current = resourceDetailsRepository.findById(id);
        if(current.isPresent()){
            return current.get();
        }
        return null;
    }

    @Override
    public ResourcesDetails get(Resource resource, ProjectColumns column) {
        List<ResourcesDetails> res = resourceDetailsRepository.findByResourceAndProjectColumns(resource,column);
        if(!res.isEmpty()){
            return null;
        }
        return res.get(-1);
    }

    @Override
    public void clear() {
        resourceDetailsRepository.deleteAll();
    }
}
