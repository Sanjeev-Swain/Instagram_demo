package com.example.Instagram_demo.service;

import com.example.Instagram_demo.dao.UserRepository;
import com.example.Instagram_demo.exception.ResourceNotFound;
import com.example.Instagram_demo.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Integer saveUser(User user){
       User userId =  userRepository.save(user);
        return userId.getUserId();
    }

    public JSONArray getUser(String userId){
        JSONArray jsonArray = new JSONArray();
        if(null != userId && userRepository.findById(Integer.valueOf(userId)).isPresent()){
            User user = userRepository.findById(Integer.valueOf(userId)).orElseThrow(()-> new ResourceNotFound("User not found with id : " + Integer.valueOf(userId)));
            JSONObject json = setUser(user);
            jsonArray.put(json);
        }
        return jsonArray;
    }
    public JSONArray getAllUser(){
        JSONArray jsonArray = new JSONArray();
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            JSONObject userObject = setUser(user);
            jsonArray.put(userObject);
        }
        jsonArray.put(userList);
        return jsonArray;
    }

    public void updateUser(String userId, User newUser){
        User user = userRepository.findById(Integer.valueOf(userId)).orElseThrow(()-> new ResourceNotFound("User not found with id : " + Integer.valueOf(userId)));
        newUser.setUserId(user.getUserId());
        userRepository.save(newUser);
    }

    public void delete(String userId) {
        if(userId!=null && userRepository.findById(Integer.valueOf(userId)).isPresent()) {
               User user = userRepository.findById(Integer.valueOf(userId)).orElseThrow(()-> new ResourceNotFound("User not found with id : " + Integer.valueOf(userId)));
               userRepository.delete(user);
        }

    }

    public JSONObject setUser(User user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getUserId());
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("phoneNUmber", user.getPhoneNumber());
        return jsonObject;
    }

}
