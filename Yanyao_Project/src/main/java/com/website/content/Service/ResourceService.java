package com.website.content.Service;


import com.website.content.entity.Resource;
import com.website.content.entity.ResourceDetail;

import java.util.Date;
import java.util.List;

public interface ResourceService {
    public Resource findOneById( Integer Id);

    public Resource findResById( Integer Id);

    List<Resource> findAll();

    public Resource save( Resource luckymoney);

    public void deleteById(Integer Id);

}
