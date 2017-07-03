package com.medley.users.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medley.users.UserDto;
import com.medley.users.service.UserService;
import com.medley.users.validation.UserServiceValidation;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserServiceValidation userServiceValidation;
	
	@RequestMapping(
			path = "/id/{userId}", 
			method = RequestMethod.GET)
	public UserDto getUser(@PathVariable("userId") Integer userId) {
		List<UserDto> usersDto = userService.getUserIds(Arrays.asList(userId));
		if (usersDto.isEmpty()) throw null;
		return usersDto.get(0);
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public UserDto createUser(@RequestBody UserDto userDto) {
		
		userServiceValidation.validateCreateDto(userDto);
		return userService.createUser(userDto);
	}
	
	@RequestMapping(
			path = "/{userId}", 
			method = RequestMethod.PUT, 
			headers = "Accept=application/json")
    public UserDto updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) {
		
	    userDto.setId(userId);
		userServiceValidation.validateUpdateDto(userDto);

		return userService.updateUser(userDto);
	}
}
