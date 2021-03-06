package com.tekcamp.users.Services.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tekcamp.users.Dto.UserDto;
import com.tekcamp.users.Exceptions.UserNotFoundException;
import com.tekcamp.users.Model.User;
import com.tekcamp.users.Services.UserServices;
import com.tekcamp.users.Shared.Utils;
import com.tekcamp.users.dao.UserRepository;

@Service
public class UserServiceImplementation implements UserServices {

	private UserRepository userRepository;
	private Utils utils; 

	public UserServiceImplementation(UserRepository userRepository, Utils utils) {
		this.userRepository = userRepository;
		this.utils = utils; 
	}

	@Override
	public List<UserDto> getAllUsers(int page, int limit) {
		
		List<User> userList = new ArrayList<User>(); 
		
		if (page>0) page --; 
		PageRequest pageableRequest = PageRequest.of(page, limit); 
		
		Page<User> userPageList = userRepository.findAll(pageableRequest);
		
		userList = userPageList.getContent(); 
		
		
		List<UserDto> userDtoList = new ArrayList<UserDto>(); 
		
		for ( int i = 0; i<userList.size(); i++ ) {
			UserDto userDto = new UserDto(); 
			BeanUtils.copyProperties(userList.get(i), userDto);
			userDtoList.add(userDto); 
		}
		
		
		return userDtoList;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto(); 
		User user;
		
		if (userRepository.findByUserId(userId) != null) {
			user = userRepository.findByUserId(userId); 
			BeanUtils.copyProperties(user, returnValue);
			return returnValue;
		} else {
			throw new UserNotFoundException(userId, " not found");
		}
		
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = new User(); 
		BeanUtils.copyProperties(userDto, user);
		
		user.setEncryptedPassword("password-test"); // how to encrypt password ? 
		user.setEmailVerification(true);            // how to verify the email ?
		user.setUserId(utils.generateUserId(20)); 
		
		User createdUser = userRepository.save(user);
		
		UserDto returnUser = new UserDto(); 
		BeanUtils.copyProperties(createdUser, returnUser);
		
		return returnUser; 
	}

	@Override
	public UserDto updateUser(UserDto userDto) {	
		User foundUser = userRepository.findByUserId(userDto.getUserId()); 
		BeanUtils.copyProperties(userDto, foundUser);	
		User updatedUser = userRepository.save(foundUser); 

		UserDto returnValue = new UserDto(); 
		BeanUtils.copyProperties(updatedUser, returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		User foundUser = userRepository.findByUserId(userId);
		userRepository.delete(foundUser); //.deleteByUserId(userId);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User foundUser = userRepository.findByEmail(email); 
		UserDto returnValue = new UserDto(); 
		BeanUtils.copyProperties(foundUser, returnValue);
		return returnValue; 
	} 
	
	
	
	
}
