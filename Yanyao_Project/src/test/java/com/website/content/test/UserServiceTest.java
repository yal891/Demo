package com.website.content.test;

import com.website.content.DAO.UserDAO;
import com.website.content.Service.UserService;
import com.website.content.Service.imp.UserServiceImp;
import com.website.content.entity.User;
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
public class UserServiceTest {


    //Test on Service Layer and inject ServiceImp
    @TestConfiguration
    static class ResourceTestServiceContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImp();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @Before
    public void setUp() {
        Date d = new Date(System.currentTimeMillis());
        User user = new User("abc", "aaaaaa",d,d,"manager");

        Mockito.when(userDAO.findUserByUsername("abc"))
                .thenReturn(user);
    }

    // write test cases here
    @Test
    public void testUserService() {
        String name = "abc";

        User found = userService.findUserByUsername("abc");
        System.out.println(found);
        Assert.assertEquals(found.getUserName(), name);
    }
}