package com.example.Instagram_demo.dao;

import com.example.Instagram_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
