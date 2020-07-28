package com.tekcamp.users.dao;

import org.springframework.data.repository.CrudRepository;

import com.tekcamp.users.Model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
