package com.example.Instagram_demo.service;

import com.example.Instagram_demo.dao.PostRepository;
import com.example.Instagram_demo.model.Post;
import com.example.Instagram_demo.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public int addPost(Post post) {
        Post postSaved = postRepository.save(post);
        return postSaved.getPostId();
    }

    public JSONArray getPosts(int userId, String postId){
        JSONArray jsonArray = new JSONArray();
        if(null!=postId && postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post post = postRepository.findById(Integer.valueOf(postId)).get();
            JSONObject json = setPost(post);
            jsonArray.put(json);
        }else{
            List<Post> postList = postRepository.findAll();
            for(Post post : postList){
                JSONObject jsonObject = setPost(post);
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }

    public void putPost(String postId, Post updatedPost) {
        if(postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post olderPost = postRepository.findById(Integer.valueOf(postId)).get();
            updatedPost.setPostId(olderPost.getPostId());
            updatedPost.setUser(olderPost.getUser());
            updatedPost.setCreatedDate(olderPost.getCreatedDate());
            Timestamp updateDate = new Timestamp(System.currentTimeMillis());
            updatedPost.setUpdatedDate(updateDate);
            postRepository.save(updatedPost);
        }
    }

    public JSONObject setPost(Post post){
        JSONObject masterJsonObject = new JSONObject();
        masterJsonObject.put("postId", post.getPostId());
        masterJsonObject.put("createdDate", post.getCreatedDate());
        masterJsonObject.put("postData", post.getPostData());

        User user = post.getUser();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("userId", user.getUserId());
        jsonObject1.put("firstName", user.getFirstName());
        jsonObject1.put("phoneNumber", user.getPhoneNumber());

        masterJsonObject.put("user", jsonObject1);

        return masterJsonObject;
    }

}
