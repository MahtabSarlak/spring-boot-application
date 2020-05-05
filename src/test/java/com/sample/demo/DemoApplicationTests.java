package com.sample.demo;

import com.sample.demo.controller.HomeController;
import com.sample.demo.model.Users;
import com.sample.demo.repository.UserRepository;
import com.sample.demo.service.UserServiceImp;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserServiceImp.class,HomeController.class})
@EnableConfigurationProperties
class DemoApplicationTests {

	@Autowired
	private HomeController controller;
	@Autowired
	private UserServiceImp userService;

	@MockBean
	private UserRepository userRepository;


	@Test
	public void findAllTest(){
		when(userRepository.findAll()).thenReturn(Stream.of(new Users("Parastoo", "F")
				, new Users("Bahar", "A")).collect(Collectors.toList()));
		assertEquals(2,userService.getAll().size());
	}
	}

