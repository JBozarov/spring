package com.tekcamp.users.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekcamp.users.Dto.UserDto;
import com.tekcamp.users.Exceptions.UserNotFoundException;
import com.tekcamp.users.Model.User;
import com.tekcamp.users.Model.Request.UserRequest;
import com.tekcamp.users.Model.Response.UserResponse;
import com.tekcamp.users.Services.UserServices; 

@RestController
@RequestMapping("users")
public class UserController {

	private UserServices userService; 
	
	public UserController(UserServices userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createdUser = userService.createUser(userDto); 
		
		UserResponse returnUser = new UserResponse(); 
		BeanUtils.copyProperties(createdUser, returnUser);
		
		return returnUser; 
	}

	@GetMapping
	public List<UserResponse> getAllUsers(
			@RequestParam(value="page", defaultValue = "1") int page, 
			@RequestParam(value="limit", defaultValue="5") int limit) {
		
		List<UserDto> userDtoList = userService.getAllUsers(page, limit);
		
		List<UserResponse> userResponseList  = new ArrayList<UserResponse>(); 
		
		for ( int i = 0; i<userDtoList.size(); i++ ) {
			UserResponse userResponse = new UserResponse(); 
			BeanUtils.copyProperties(userDtoList.get(i), userResponse);
			userResponseList.add(userResponse); 
		}
	
		return userResponseList; 
	}
	
	
	@GetMapping(path="/{userId}")
	public UserResponse getUserByUserId(@PathVariable String userId) {
		UserDto singleUserDto = userService.getUserByUserId(userId); 
		UserResponse returnValue = new UserResponse(); 
		
//			if (returnValue.getUserId()==null) {
//				throw new UserNotFoundException(singleUserDto.getFirst_name(), " not found"); 
//
//			}
		BeanUtils.copyProperties(singleUserDto, returnValue);
		return returnValue; 
	}
	

	@PutMapping(path="/{userId}")
	public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable String userId) {
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);
		
		userDto.setUserId(userId);
		
		UserDto updatedUserDto = userService.updateUser(userDto); 
		
		UserResponse returnValue = new UserResponse(); 
		BeanUtils.copyProperties(updatedUserDto, returnValue);
		
		return returnValue; 
	}
	
	@DeleteMapping(path="/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId); 
	}
	
	@GetMapping(path="/email/{email}")
	public UserResponse getUserByEmail(@PathVariable String email) {
		UserDto userDto = userService.getUserByEmail(email);
		UserResponse returnValue = new UserResponse(); 
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue; 
	}
	
}











