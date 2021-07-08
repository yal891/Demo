package com.website.content.test;

import com.website.content.DAO.ProjectDAO;
import com.website.content.entity.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith (SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectDAO projectDAO;

    // write test cases here
    @Test
    public void testFindResource() {
        // given

        Date d = new Date(System.currentTimeMillis());
        Project res_project = new Project("qqq",d,d);

        entityManager.persist(res_project);
        entityManager.flush();

        Project found = projectDAO.findByProject_name(res_project.getProject_name());
        Assert.assertEquals(found.getProject_name(), res_project.getProject_name());


    }

}
