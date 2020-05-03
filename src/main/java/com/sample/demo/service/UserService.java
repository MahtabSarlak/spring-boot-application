package com.sample.demo.service;

import com.sample.demo.dto.UserDto;
import com.sample.demo.model.Users;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();
    public void saveUser(Users user);
    public void saveInitUsers(List<Users> users);
}
