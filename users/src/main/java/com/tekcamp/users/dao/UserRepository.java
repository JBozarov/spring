package com.tekcamp.users.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tekcamp.users.Model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findByUserId(String userId); 
	
	User findByEmail(String email);
	
}
