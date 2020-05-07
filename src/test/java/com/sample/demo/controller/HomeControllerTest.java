package com.sample.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.DemoApplication;
import com.sample.demo.dto.UserDto;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.service.UserServiceImp;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes= DemoApplication.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImp userServiceImp;
    
    @Autowired
    ObjectMapper objectMapper;

    private UserDto sampleUserDto;
    private UserDto sampleUpdateUserDto;
    private List<UserDto> userDtos;
    private String sampleFirstName;
    private String sampleLikeFirstName;
    private long sampleId=3L;
    @BeforeAll
    void beforeAll(){
        sampleUserDto =  new UserDto(3L,"zahra","k","12324");
        userDtos = Arrays.asList(sampleUserDto);
        sampleFirstName="zahra";
        sampleLikeFirstName="zah";
        sampleUpdateUserDto = new UserDto(3L,"zahra2","k","12324");
    }

    @Test
    void create() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sampleUserDto)))
                         .andExpect(status().isOk());

    }

    @Test
    void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sampleUpdateUserDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception{
        // given
        given(userServiceImp.getAll()).willReturn(userDtos);

        // when + then

        mockMvc.perform(MockMvcRequestBuilders.get("/showAll"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':3,'firstName':'zahra', 'lastName':'k','password':'12324'}]"));

    }

    @Test
    void getUserById() throws Exception{
        // given
        given(userServiceImp.getUserById(sampleId)).willReturn(sampleUserDto);

        // when + then

        mockMvc.perform(MockMvcRequestBuilders.get("/getUserById")
                        .param("id",String.valueOf(sampleId)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':3,'firstName':'zahra', 'lastName':'k','password':'12324'}"));
    }

    @Test
    void getUserByFirstName()throws Exception {
        // given

        given(userServiceImp.getUserByFirstName(sampleFirstName)).willReturn(userDtos);

        // when + then

        mockMvc.perform(MockMvcRequestBuilders.get("/getUserByFirstName")
                .param("firstName",sampleFirstName))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':3,'firstName':'zahra', 'lastName':'k','password':'12324'}]"));
    }

    @Test
    void getUserByFirstNameLike() throws Exception{
        // given

        given(userServiceImp.getUserByFirstNameLike(sampleLikeFirstName)).willReturn(userDtos);

        // when + then

        mockMvc.perform(MockMvcRequestBuilders.get("/getUserByFirstNameLike")
                .param("name",sampleLikeFirstName))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':3,'firstName':'zahra', 'lastName':'k','password':'12324'}]"));
    }

    @Test
    void deleteUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
                .param("id",String.valueOf(sampleId)))
                .andExpect(status().isOk())
                .andExpect(content().string("User with given id deleted"));
    }
}