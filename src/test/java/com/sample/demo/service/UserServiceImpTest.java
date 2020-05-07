package com.sample.demo.service;

import com.sample.demo.controller.HomeController;
import com.sample.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserServiceImp.class,HomeController.class})
@EnableConfigurationProperties
class UserServiceImpTest {

    @Autowired
    private HomeController controller;
    @Autowired
    private UserServiceImp userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    void getAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByFirstName() {
    }

    @Test
    void getUserByFirstNameLike() {
    }
}