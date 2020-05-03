package com.sample.demo.repository;

import com.sample.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository  extends JpaRepository<Users,Long> {
        List<Users> findAll();

}
