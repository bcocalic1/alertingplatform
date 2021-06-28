package com.example.user_management_mikroservis.services;

import com.example.user_management_mikroservis.dao.UserDao;
import com.example.user_management_mikroservis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("user") UserDao userDao) {
        this.userDao = userDao;
    }

    public void register(User user) throws Exception {
        this.userDao.registerUser(user);
    }

    public boolean login(String email, String password){
        return this.userDao.login(email, password);
    }

    public List<User> getAll(){
        return this.userDao.getAll();
    }
}
