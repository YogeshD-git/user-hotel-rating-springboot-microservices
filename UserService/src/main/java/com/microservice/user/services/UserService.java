package com.microservice.user.services;

import java.util.List;

import com.microservice.user.entities.User;

public interface UserService {
	
	//user operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user by given userId
	User getUser(String userId);
	
	//delete 
	void deleteUser(String userId);
	
	//update
	User updateUser(String userId);

}
