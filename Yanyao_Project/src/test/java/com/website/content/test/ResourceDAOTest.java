package com.website.content.test;

import com.website.content.DAO.ResourceDAO;
import com.website.content.entity.Resource;
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
public class ResourceDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ResourceDAO resourceRepository;

    // write test cases here
    @Test
    public void testFindResource() {
        // given

        Date d = new Date(System.currentTimeMillis());
        Resource res = new Resource(123123, "hello", false,  d,d);

        entityManager.persist(res);
        entityManager.flush();

        Resource found = resourceRepository.findById(res.getResourceCode()).get();
        Assert.assertEquals(found.getResourceName(), res.getResourceName());
        Assert.assertEquals(found.getResourceCode(), res.getResourceCode());

    }

}