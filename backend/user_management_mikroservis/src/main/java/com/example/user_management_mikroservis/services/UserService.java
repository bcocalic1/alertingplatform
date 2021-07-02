package com.example.user_management_mikroservis.services;

import com.example.user_management_mikroservis.dao.UserRepository;
import com.example.user_management_mikroservis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("users") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   public void register(User user) throws Exception {
        this.userRepository.save(user);
    }

    public boolean login(String email, String password){
        User user = this.userRepository.findByEmailAddress(email);
        if(user.getPassword().equals(password)) return true;
        return false;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
