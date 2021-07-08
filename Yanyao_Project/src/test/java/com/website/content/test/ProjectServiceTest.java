package com.website.content.test;

import com.website.content.DAO.ProjectDAO;
import com.website.content.Service.ProjectService;
import com.website.content.Service.imp.ProjectServiceImp;
import com.website.content.entity.Project;
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
public class ProjectServiceTest {


    @TestConfiguration
    static class ResourceTestServiceContextConfiguration {

        @Bean
        public ProjectService resourceService() {
            return new ProjectServiceImp();
        }
    }

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectDAO projectDAO;

    @Before
    public void setUp() {
        Date d = new Date(System.currentTimeMillis());
        Project _project = new Project("qqq",d,d);

        Mockito.when(projectDAO.findByProject_name("qqq"))
                .thenReturn(_project);
    }

    // write test cases here
    @Test
    public void testProjectService() {
        String name = "qqq";

        Project found = projectService.findOneByProjectName(name);
//        System.out.println(found);
        Assert.assertEquals(found.getProject_name(), name);
    }
}