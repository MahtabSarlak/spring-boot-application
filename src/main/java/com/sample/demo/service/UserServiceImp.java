package com.sample.demo.service;

import com.sample.demo.controller.HomeController;
import com.sample.demo.dto.UserDto;
import com.sample.demo.model.Users;
import com.sample.demo.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository repository;

    private static final Logger logger = LogManager.getLogger(HomeController.class);


    public List<UserDto> findAll() {
        logger.info("Return all users data");
        List<Users> users = repository.findAll();
        List<UserDto> userdtos = new ArrayList<>();
        for (Users user : users) {
            userdtos.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName()));
        }
        return userdtos;
    }

    public void createOrUpdate(Users user) {
        Optional<Users> users = repository.findById(user.getId());
        if (users.isPresent()) {
            logger.info("Update user with given id");
            Users temp = users.get();
            temp.setId(user.getId());
            temp.setLastName(user.getLastName());
            temp.setFirstName(user.getFirstName());
            repository.save(temp);
        } else {
            logger.info("Save new user");
            repository.save(user);
        }
    }

    public void saveInitUsers(List<Users> users) {
        logger.info("Initial database with users");
        repository.saveAll(users);
    }
}
