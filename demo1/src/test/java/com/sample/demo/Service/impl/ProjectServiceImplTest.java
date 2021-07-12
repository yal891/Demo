package com.sample.demo.Service.impl;

import com.sample.demo.Entity.Project;
import com.sample.demo.Service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
class ProjectServiceImplTest {

    @Autowired
    private ProjectService service;
    @Test
    void saveProject() {
        Project project = new Project();
        project.setProjectName("test");
        project.setProjectCode(BigInteger.valueOf(123456));
        service.saveProject(project);
        System.out.println(project);
    }
}
