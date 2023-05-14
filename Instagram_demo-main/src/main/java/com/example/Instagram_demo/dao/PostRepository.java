package com.example.Instagram_demo.dao;

import com.example.Instagram_demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
