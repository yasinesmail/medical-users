package com.medley.users.service;

import java.util.List;

import com.medley.users.UserDto;

public interface UserService {
	
	public List<UserDto> getUserIds(List<Integer> userIds);
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto updateUser(UserDto userDto);

}
