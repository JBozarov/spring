package com.tekcamp.users.Services;

import java.util.List;

import com.tekcamp.users.Dto.UserDto;
import com.tekcamp.users.Model.User;

public interface UserServices {

	List<User> getAllUsers(int page, int limit);

	User getUserById(Long id);

	UserDto createUser(UserDto userDto);

	void updateUser(User user);

	void deleteUser(Long id);

	User getUserByEmail(String email);

}
