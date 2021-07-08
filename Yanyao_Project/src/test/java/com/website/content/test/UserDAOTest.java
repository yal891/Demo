/* DataJpaTest*/

package com.website.content.test;

import com.website.content.DAO.UserDAO;
import com.website.content.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;


@RunWith (SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testFindUser() {
        Date d = new Date(System.currentTimeMillis());

        User user = new User("ABC", "abc",d,d,"boss");

        entityManager.persist(user);
        entityManager.flush();

        User found = userDAO.findUserByUsername(user.getUserName());
        System.out.println(found);
        Assert.assertEquals(found.getUserName(), user.getUserName());

    }

}



/* Spring Boot Test. Prefer not to insert data into db for test*/
//package com.website.content.test;
//
//import com.website.content.DAO.UserDAO;
//import com.website.content.entity.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//
//@RunWith (SpringRunner.class)
//@SpringBootTest
//
//public class UserDAOTest {
//
//
//    @Autowired
//    private UserDAO userDAO;
//
//    // write test cases here
//    @Test
//    public void testUserDao() {
//        // given
//        String name = "abc";
//        Date d = new Date(System.currentTimeMillis());
//        User user = new User();
//        user.setUserName("abc");
//        user.setPassword("123456");
//        user.setTime_created(d);
//        user.setTime_updated(d);
//        user.setTitle("boss");
//
//        User res = userDAO.save(user);
//        Assert.assertNotNull(res);
//        System.out.println("------------------");
////        User found = userDAO.findUserByUsername(name);
////        System.out.println(found);
//        Assert.assertEquals("abc", user.getUserName());
//    }
//
//}