package com.sample.demo.service;

import com.sample.demo.controller.HomeController;
import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
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


    public List<UserDto> getAll() {
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
            logger.info("Update user with id :"+users.get().getId());
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

    @Override
    public void deleteUserById(Long id) throws RecordNotFoundException {
        Optional<Users> user= repository.findById(id);
        if (user.isPresent()) {
            logger.info("Delete user with given "+id);
            repository.deleteById(id);
        } else {
            logger.info("No user record exist for given id");
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

    @Override
    public UserDto getUserById(Long id) throws RecordNotFoundException {
        Optional<Users> user= repository.findById(id);
        if (user.isPresent()) {
            logger.info("Find user with given "+id);
            Users users = user.get();
            return new UserDto(users.getId(),users.getFirstName(),users.getLastName());
        } else {
            logger.info("No user record exist for given id");
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }

}
