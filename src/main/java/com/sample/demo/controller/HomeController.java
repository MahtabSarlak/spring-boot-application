package com.sample.demo.controller;

import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Users;
import com.sample.demo.service.UserServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class HomeController {
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    public HomeController() {
    }

    public HomeController(UserServiceImp userService) {
        this.userService = userService;
    }

    @Autowired
    private UserServiceImp userService;

    @PostMapping(value = "/create")
    public @ResponseBody
    String create(@RequestBody @Valid UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid user");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                logger.error(error.getDefaultMessage());
            }
            return "Invalid user";
        }
        Users temp_user;
        if (isNull(user.getId())) {
            temp_user = new Users(user.getFirstName(), user.getLastName(),user.getPassword());
            userService.create(temp_user);
            return "New user is created";
        }
        temp_user = new Users(user.getId(), user.getFirstName(), user.getLastName(),user.getPassword());
        userService.create(temp_user);
        return "Update user with given id";
    }
    @PostMapping(value = "/update")
    public @ResponseBody
    String update(@RequestBody @Valid UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Invalid user");
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                logger.error(error.getDefaultMessage());
            }
            return "Invalid user";
        }
        Users temp_user;
        if (isNull(user.getId())) {
            temp_user = new Users(user.getFirstName(), user.getLastName(),user.getPassword());
            userService.update(temp_user);
            return "New user is created";
        }
        temp_user = new Users(user.getId(), user.getFirstName(), user.getLastName(),user.getPassword());
        userService.update(temp_user);
        return "Update user with given id";
    }

    @GetMapping("/showAll")
    public @ResponseBody
    List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/getUserById")
    public UserDto getUserById(@RequestParam("id") Long id) throws RecordNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/getUserByFirstName")
    public List<UserDto> getUserByFirstName(@RequestParam("firstName") String firstName) {
        return userService.getUserByFirstName(firstName);
    }

    @GetMapping("/getUserByFirstNameLike")
    public List<UserDto> getUserByFirstNameLike(@RequestParam("name") String name) {
        return userService.getUserByFirstNameLike(name);
    }

    @DeleteMapping("/delete")
    public String deleteUserById(@RequestParam("id") Long id) throws RecordNotFoundException {
        userService.deleteUserById(id);
        return "User with given id deleted";
    }
}
