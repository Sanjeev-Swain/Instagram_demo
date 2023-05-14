package com.example.Instagram_demo.controller;

import com.example.Instagram_demo.model.User;
import com.example.Instagram_demo.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.annotation.security.PermitAll;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody String user){
        User addingUser = setUser(user);
        Integer responseId = userService.saveUser(addingUser);
        return  new ResponseEntity("user is added to database having Id " + responseId, HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity getUser(@RequestParam String userId){
        JSONArray jsonArray = userService.getUser(userId);
        return  new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity getUser(){
        JSONArray jsonArray = userService.getAllUser();
        return  new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
    }
    @PutMapping("/putUser/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId, @RequestBody String userData){
        User user = setUser(userData);
        userService.updateUser(userId, user);
        return new ResponseEntity<>("User is updated", HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        userService.delete(userId);
        return new ResponseEntity<>("User is deleted", HttpStatus.OK);
    }


    public  User setUser(String userData){
        JSONObject jsonObject = new JSONObject(userData);
        User newuser = new User();
        newuser.setUserId(jsonObject.getInt("userId"));
        newuser.setFirstName(jsonObject.getString("firstName"));
        newuser.setLastName(jsonObject.getString("lastName"));
        newuser.setAge(jsonObject.getInt("age"));
        newuser.setEmail(jsonObject.getString("email"));
        newuser.setPhoneNumber(jsonObject.getString("phoneNumber"));
        return newuser;
    }

}
