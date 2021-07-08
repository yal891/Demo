package com.website.content.Service.imp;

import com.website.content.DAO.ResourceDAO;
import com.website.content.entity.Resource;
import com.website.content.Service.ResourceService;
import com.website.content.entity.ResourceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional // Guarantee all manipulates all completed
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    private ResourceDAO resourceDao;

    @Override
    public Resource findOneById( Integer Id) {
        return resourceDao.findById(Id).orElse(null);
    }

    @Override
    public Resource findResById( Integer Id) {
        return resourceDao.findByResourceCode(Id);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Resource save( Resource resource ) {
        return resourceDao.save(resource);
    }

    @Override
    public void deleteById(Integer id){
        resourceDao.deleteById(id);
    }

}