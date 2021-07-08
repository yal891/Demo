package com.website.content.test;

import com.website.content.DAO.ResourceDAO;
import com.website.content.Service.ResourceService;
import com.website.content.Service.imp.ResourceServiceImpl;
import com.website.content.entity.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith (SpringRunner.class)
public class ResourceServiceTest {

    @TestConfiguration
    static class ResourceTestServiceContextConfiguration {

        @Bean
        public ResourceService resourceService() {
            return new ResourceServiceImpl();
        }
    }

    @Autowired
    private ResourceService resourceService;

    @MockBean
    private ResourceDAO resourceRepository;

    @Before
    public void setUp() {
        Date d = new Date(System.currentTimeMillis());
        Resource res = new Resource(123456, "name", false,  d,d);

        Mockito.when(resourceRepository.findByResourceCode(123456))
                .thenReturn(res);
    }

    // write test cases here
    @Test
    public void testResourceService() {
        String name = "name";
        Integer id = 123456;

        Resource found = resourceService.findResById(id);
        System.out.println(found);
        Assert.assertEquals(found.getResourceName(), name);
        Assert.assertEquals(found.getResourceName(), name);
    }
}