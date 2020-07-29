package com.tekcamp.users.Services;

import java.util.List;

import com.tekcamp.users.Model.User;

public interface UserServices {

	List<User> getAllUsers();

	User getOneUser(Long id);

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(Long id);

}
