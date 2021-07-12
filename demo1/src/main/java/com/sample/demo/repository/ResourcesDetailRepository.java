package com.sample.demo.repository;


import com.sample.demo.Entity.ProjectColumns;
import com.sample.demo.Entity.Resource;
import com.sample.demo.Entity.ResourcesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesDetailRepository extends JpaRepository<ResourcesDetails, Integer> {

    List<ResourcesDetails> findByResourceAndProjectColumns(Resource resource, ProjectColumns column);
}
