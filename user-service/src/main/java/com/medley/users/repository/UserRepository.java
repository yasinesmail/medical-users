package com.medley.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medley.users.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
}
