package com.tekcamp.users.Services.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tekcamp.users.Model.User;
import com.tekcamp.users.Services.UserServices;
import com.tekcamp.users.dao.UserRepository;

@Service
public class UserServiceImplementation implements UserServices {

	private UserRepository userRepository;

	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		return users;
	}

	@Override
	public User getOneUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id); 
		User user = optionalUser.get(); 
		return user;
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user); 
	}

	@Override
	public void updateUser(User user) {
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll(); 
		for (int i = 0; i<users.size(); i++ ) {
			if (users.get(i).getId() == user.getId()) {
				userRepository.save(user); 
			}
		}
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	} 
	
}
