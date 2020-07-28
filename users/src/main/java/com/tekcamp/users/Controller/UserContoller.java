package com.tekcamp.users.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekcamp.users.Model.User;
import com.tekcamp.users.Services.UserServices; 

@RestController
@RequestMapping("users")
public class UserContoller {

	private UserServices userService; 
	
	public UserContoller(UserServices userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users; 
	}
	
	@GetMapping(path="/{id}")
	public User getSingleUser(@PathVariable Long id) {
		User oneUser = userService.getOneUser(id); 
		return oneUser; 
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.createUser(user); 
	}
}
