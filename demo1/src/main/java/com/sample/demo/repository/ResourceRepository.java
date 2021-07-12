package com.sample.demo.repository;

import com.sample.demo.Entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
   // Resource getResourcesById(Integer resource_id);


}
