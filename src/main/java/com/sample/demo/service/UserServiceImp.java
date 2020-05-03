package com.sample.demo.service;

import com.sample.demo.controller.HomeController;
import com.sample.demo.dto.UserDto;
import com.sample.demo.model.Users;
import com.sample.demo.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserServiceImp implements UserService{
    private static final Logger logger = LogManager.getLogger(HomeController.class);
    @Autowired
    private UserRepository repository;

    public List<UserDto> findAll() {
        /*logger.info("Return all users data");*/
        List<Users> users = repository.findAll();
        List<UserDto> userdtos = new ArrayList<>();
        for (Users user : users) {
            userdtos.add(new UserDto(user.getId(),user.getFirstName(), user.getLastName()));
        }
        return userdtos;
    }

    public void saveUser(Users user) {
       logger.info("Save new user");
        repository.save(user);
    }

    public void saveInitUsers(List<Users> users) {
      logger.info("Initial database with users");
        repository.saveAll(users);
    }
}
