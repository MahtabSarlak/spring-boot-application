package com.sample.demo.service;

import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Users;

import java.util.List;

public interface UserService {
    public List<UserDto> getAll();

    public Users create(Users user);

    public Users update(Users user);

    public void deleteUserById(Long id) throws RecordNotFoundException;

    public UserDto getUserById(Long id) throws RecordNotFoundException;

    public List<UserDto> getUserByFirstName(String firstName);

    public List<UserDto> getUserByFirstNameLike(String firstName);

}
