package com.website.content.DAO;
import com.website.content.entity.Project;
import com.website.content.entity.Resource;
import com.website.content.entity.ResourceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectDAO extends JpaRepository<Project, Integer>{
    @Query(value = "SELECT * FROM project WHERE user_id=?", nativeQuery = true)
    public List<Project> findALLByUserId(Integer userID);


    @Query(value = "SELECT * FROM project WHERE project_name=?", nativeQuery = true)
    public Project findByProject_name(String name);


}
