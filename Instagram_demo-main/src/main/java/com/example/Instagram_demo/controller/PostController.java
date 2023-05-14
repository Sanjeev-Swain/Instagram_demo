package com.example.Instagram_demo.controller;

import com.example.Instagram_demo.dao.UserRepository;
import com.example.Instagram_demo.dto.ApiResponse;
import com.example.Instagram_demo.model.Post;
import com.example.Instagram_demo.model.User;
import com.example.Instagram_demo.service.PostService;
import jakarta.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/savePost")
    public ResponseEntity savePost(@RequestBody String postData){
        Post post = setPost(postData);
        int postId = postService.addPost(post);
        return new ResponseEntity<>("Post is added "+postId, HttpStatus.CREATED);
    }
    @GetMapping("/getPost")
    public ResponseEntity getPost(@RequestParam String userId, @Nullable @RequestParam String postId){
        JSONArray jsonArray = postService.getPosts(Integer.valueOf(userId),postId);
        return new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
    }

    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<ApiResponse> update(@PathVariable String postId, @RequestBody String postData){
        Post post = setPost(postData);
        postService.putPost(postId, post);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post is Updated", true), HttpStatus.OK);
    }

    public Post setPost(String posData){
        JSONObject json = new JSONObject(posData);
        int userId = json.getInt("userId");
        User newUser = null;
           if(userRepository.findById(userId).isPresent()){
               newUser = userRepository.findById(userId).get();
           }else{
               return null;
           }
        Post newPost = new Post();
        newPost.setUser(newUser);
        newPost.setPostId(json.getInt("postId"));
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        newPost.setCreatedDate(createdTime);
        newPost.setPostData(json.getString("postData"));
        return newPost;
    }
}
