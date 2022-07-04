package com.example.TodoListApi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    //To create a test class press (ctrl+shift+T)
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    public UserService() {
        super();
    }


    public User registerNewUser(UserModel model){
        if (model.getEmail() == null || !model.getEmail().contains("@"))
            return null;

        User u = new User();
        if (!userRepository.existsByEmail(model.getEmail())) {

            u.setEmail(model.getEmail());
            u.setUsername(model.getEmail());
            u.setPassword(passwordEncoder.encode(model.getPassword()));
            userRepository.save(u);

        }
        System.out.println(u);
        if (u.getEmail() == null)
            return null;
        else
            return u;
    }




}
