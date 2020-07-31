package com.tekcamp.users.Services;

import java.util.List;

import com.tekcamp.users.Dto.UserDto;
import com.tekcamp.users.Model.User;

public interface UserServices {

	List<UserDto> getAllUsers(int page, int limit);

	UserDto getUserByUserId(String userId);

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto);

	void deleteUser(String userId);

	User getUserByEmail(String email);

}
