package com.tekcamp.users.Services;

import java.util.List;

import com.tekcamp.users.Dto.UserDto;
import com.tekcamp.users.Model.User;

public interface UserServices {

	List<UserDto> getAllUsers(int page, int limit);

	UserDto getUserByUserId(String userId);

	UserDto createUser(UserDto userDto);

	void updateUser(User user);

	void deleteUser(Long id);

	User getUserByEmail(String email);

}
