package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Resource;
import com.sample.demo.Service.ResourceService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ResourceServiceImplTest {
    @Autowired
    private ResourceService service;
    @Test
    void saveResource() {
        Resource resource = new Resource();
        resource.setResource_id(2);
        service.saveResource(resource);
        System.out.println(resource);
    }
}
