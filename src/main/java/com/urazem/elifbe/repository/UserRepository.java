package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByFirstName(String first_name);
}