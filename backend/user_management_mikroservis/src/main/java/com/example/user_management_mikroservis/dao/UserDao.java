package com.example.user_management_mikroservis.dao;

import com.example.user_management_mikroservis.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("user")
public class UserDao {
    private List<User> users;

    public UserDao() {
        this.users = new ArrayList<>();
    }

    public void registerUser(User user) throws Exception {
        User userDB = getUserByEmail(user.getEmail());
        if(!Objects.isNull(userDB)){
            throw new Exception("User already exists");
        }
        this.users.add(user);
    }

    public boolean login(String email, String password){
        User userDB = this.getUserByEmail(email);
        if(Objects.isNull(userDB)) throw  new RuntimeException("User doesn't exist") ;
        if(!userDB.getPassword().equals(password)) return false;
        return true;
    }

    public User getUserByEmail(String email){
        for (User user: this.users) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public List<User> getAll(){
        return this.users;
    }
}
