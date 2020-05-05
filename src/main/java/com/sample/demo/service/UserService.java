package com.sample.demo.service;

import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Users;

import java.util.List;

public interface UserService {
    public List<UserDto> getAll();

    public void create(Users user);
    public void update(Users user);
    public void saveInitUsers(List<Users> users);

    public void deleteUserById(Long id) throws RecordNotFoundException;

    public UserDto getUserById(Long id) throws RecordNotFoundException;

}
