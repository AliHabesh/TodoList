package com.example.TodoListApi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UserModel model){

        System.out.println(model);
        User user = userService.registerNewUser(model);
        if (user == null){
            throw new RuntimeException("Email already in use");
        }

        return ResponseEntity.ok("Successful registration");


    }
}
