package com.sample.demo.service;

import com.sample.demo.controller.HomeController;
import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Users;
import com.sample.demo.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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


    private Users sampleUser;
    private Users sampleUpdateUser;
    private List<Users> users;
    private String sampleFirstName;
    private String sampleLikeFirstName;
    private long sampleId=3L;

    @BeforeAll
    void beforeAll(){
        addUser();
        //when(userRepository.findAll()).thenReturn(users);
        sampleUser =  new Users(3L,"sample_user","sample_user_lastname","sample_user_pass");
        sampleFirstName="sample_user";
        sampleLikeFirstName="user";
        sampleUpdateUser = new Users(3L,"sample_user_update","sample_user_lastname","sample_user_pass");
        List<Users> tmp = new ArrayList<>();
        tmp.add(users.get(0));
        when(userRepository.findByFirstName("user_name1")).thenReturn(Arrays.asList(users.get(0)));
        when(userRepository.findByFirstNameContains("user")).thenReturn(users);
    }


    private void addUser()
    {
        users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Users user = new Users();
            user.setFirstName("user_name" + i);
            user.setLastName("user_lastname" + i);
            user.setPassword("user_pass" + i);
            user.setId((long) i);
            users.add(user);
        }
    }

    @Test
    void create() {
        when(userRepository.save(sampleUser)).thenReturn(sampleUser);
        Users result = userService.create(sampleUser);
        assertEquals("sample_user_pass", result.getPassword());
        assertEquals("sample_user", result.getFirstName());
        assertEquals("sample_user_lastname", result.getLastName());

    }

    @Test
    void getAll() {
        when(userRepository.findAll()).thenReturn(users);
        System.out.println(users.size());
        List<UserDto> dtos = userService.getAll();
        List<Users> result = new ArrayList<>();
        for (UserDto user : dtos) {
            result.add(new Users(user.getId(), user.getFirstName(), user.getLastName(),user.getPassword()));
        }
        assertEquals(users.toString(), result.toString());
        assertEquals(10,result.size());
    }

    @Test
    void update() {
        Users result = userService.update(sampleUpdateUser);
        assertEquals("sample_user_update", result.getFirstName());
        assertEquals(sampleUpdateUser.toString(), result.toString());
    }

    @Test
    void deleteUserById() throws RecordNotFoundException {
        when(userRepository.findById(sampleId)).thenReturn(Optional.of(users.get((int) sampleId)));
        userService.deleteUserById(sampleId);
    }

    @Test
    void getUserById() throws RecordNotFoundException {
        when(userRepository.findById(sampleId)).thenReturn(Optional.of(users.get((int) sampleId)));
        UserDto result = userService.getUserById(sampleId);
        Users temp_user = new Users();
        temp_user.setId(result.getId());
        temp_user.setFirstName(result.getFirstName());
        temp_user.setLastName(result.getLastName());
        temp_user.setPassword(result.getPassword());
        assertEquals(users.get(3).toString(),temp_user.toString());
    }

    @Test
    void getUserByFirstName() {
        when(userRepository.findByFirstName("user_name1")).thenReturn(Arrays.asList(users.get(0)));
        List<UserDto> result = userService.getUserByFirstName("user_name1");
        System.out.println(result.get(0).getId());
        assertEquals(result.get(0).getId(), Long.valueOf(0));
    }

    @Test
    void getUserByFirstNameLike() {
        when(userRepository.findByFirstNameContains("user")).thenReturn(users);
        List<UserDto> dtos = userService.getUserByFirstNameLike("user");
        List<Users> result = new ArrayList<>();
        for (UserDto user : dtos) {
            result.add(new Users(user.getId(), user.getFirstName(), user.getLastName(),user.getPassword()));
        }
        assertEquals(result.toString(),users.toString());
    }
}