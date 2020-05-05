package com.sample.demo.controller;

import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Users;
import com.sample.demo.service.UserServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

    @GetMapping(value = "/")
    public @ResponseBody
    String homePage() {
        logger.info("Enter home page");
        return "Welcome!!!";
    }


    @GetMapping("/init")
    public @ResponseBody
    String initDb() {

        userService.saveInitUsers(Arrays.asList(new Users("Kiana", "S")
                , new Users("Parastoo", "F")
                , new Users("Bahar", "A")
        ));
        return "initail users are created";
    }

    @PostMapping(value = {"/create","/update"})
    public @ResponseBody
    String create(@RequestBody @Valid UserDto user , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            logger.error("Invalid user");
            return "Invalid user";
        }
        Users temp_user ;
        if(isNull(user.getId()))
        {
             temp_user= new Users(user.getFirstName(), user.getLastName());
            userService.createOrUpdate(temp_user);
            return "New user is created";
        }else{
            temp_user= new Users(user.getId(),user.getFirstName(), user.getLastName());
            userService.createOrUpdate(temp_user);
            return "Update user with given id";
        }
    }

    @GetMapping("/showAll")
    public @ResponseBody
    List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/getUser")
    public UserDto getUserById(@RequestParam("id") Long id) throws RecordNotFoundException {
        UserDto user = userService.getUserById(id);
        return user;
    }

    @DeleteMapping("/delete")
    public String deleteUserById(@RequestParam("id") Long id) throws RecordNotFoundException {
        userService.deleteUserById(id);
        return "User with given id deleted";
    }
}
