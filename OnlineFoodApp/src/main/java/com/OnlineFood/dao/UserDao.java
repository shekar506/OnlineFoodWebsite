package com.OnlineFood.dao;

import java.util.List;

import com.OnlineFood.model.User;

public interface UserDao {
	
	int insertUser(User user);
    List<User> getAllUsers();
    User getUserByEmail(String email);
    int deleteUserById(int id);
    int updateUserById(int id,String address);
    

}
