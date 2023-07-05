package com.example.demo;

import java.util.List;
import java.util.Optional;
//import javax.transaction.Transactional;

import java.util.ArrayList;    
import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.stereotype.Service;

import com.example.data.User;

@Service    
public class UserService   
{    
    @Autowired    
    private UserRepository UserRepository;
   /* @Autowired
    private PasswordEncoder passwordEncoder;*/
  

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        UserRepository.findAll().forEach(users::add);
        return users;
    }
    public Optional<User> getUser(User user) throws UserAlreadyExistException{ 
        Optional<User> findUser= UserRepository.findByUsername(user.getUsername());
        
        if (!findUser.isPresent()){
            throw new UserAlreadyExistException("User not found!");
        } 
        else if(findUser.isPresent() && !findUser.get().getPassword().equals(user.getPassword()))
        {
            throw new UserAlreadyExistException("Wrong Password!");

        }
        
        return UserRepository.findByUsername(user.getUsername()); 
    }

    public void addUser(User user) throws UserAlreadyExistException{
        if (UserRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Username used!");
        }
        else if(!user.getPassword().equals(user.getMatchingPassword())){
            throw new UserAlreadyExistException("Matching password error!");
        }
        UserRepository.save(user);
    }
    

}    