package com.example.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.exceptions.UserAlreadyExists;
import com.example.todo.exceptions.UserNotFound;
import com.example.todo.models.User;
import com.example.todo.repository.UserRepo;

@Service

public class UserService {
    @Autowired 
    private UserRepo userRepo;
    public User registerUser(User user)  {
        if(userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExists("User already exists with this email");  
        }
        return userRepo.save(user);
    }


    public User loginUser(String email, String password) throws UserNotFound {
        User loginUser=userRepo.findByEmail(email);
        if(loginUser!=null && loginUser.getPassword().equals(password)) {
            return loginUser;
        } 
        throw new UserNotFound("User not found with this email");
    }
 
    public User getUserById(int id) throws UserNotFound {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFound("User not found with this id"));
    }
    
}
