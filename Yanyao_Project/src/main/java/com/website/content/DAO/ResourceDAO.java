
package com.website.content.DAO;

import com.website.content.entity.Resource;
import com.website.content.entity.ResourceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDAO extends JpaRepository<Resource, Integer>{
    public Resource findByResourceCode(Integer id);


}
