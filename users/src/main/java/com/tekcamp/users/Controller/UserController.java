package com.tekcamp.users.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tekcamp.users.Model.User;
import com.tekcamp.users.Services.UserServices; 

@RestController
@RequestMapping("users")
public class UserController {

	private UserServices userService; 
	
	public UserController(UserServices userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers(
			@RequestParam(value="page", defaultValue = "1") int page, 
			@RequestParam(value="limit", defaultValue="5") int limit) {
		List<User> users = userService.getAllUsers(page, limit);
		return users; 
	}
	
	@GetMapping(path="/{id}")
	public User getUserById(@PathVariable Long id) {
		User oneUser = userService.getUserById(id); 
		return oneUser; 
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.createUser(user); 
	}
	
	@PutMapping
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user); 
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id); 
	}
	
	@GetMapping(path="/email/{email}")
	public User getUserByEmail(@PathVariable String email) {
		User oneUser = userService.getUserByEmail(email);
		return oneUser; 
	}
	
}











