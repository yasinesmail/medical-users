package com.medley.users.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medley.users.ProfessionTypeDto;
import com.medley.users.UserDto;
import com.medley.users.dtomapper.ProfessionTypeMapper;
import com.medley.users.dtomapper.UserMapper;
import com.medley.users.exceptions.NotFoundException;
import com.medley.users.model.ProfessionType;
import com.medley.users.model.User;
import com.medley.users.repository.ProfessionTypeRepository;
import com.medley.users.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;
	
	@Resource
	private ProfessionTypeRepository professionTypeRepository;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource 
	ProfessionTypeMapper professionTypeMapper;
	
	@Resource
	private ProfessionTypeService professionTypeService;
	
	private Map<String, ProfessionTypeDto> getProfessionalType() {
		
		List<ProfessionTypeDto> professionalTypes = professionTypeService.getProfessionTypes();
		return professionalTypes.stream().collect( Collectors.toMap( p -> p.getName().toLowerCase(), p -> p ) );
	}
	
	@Override
	public List<UserDto> getUserIds(List<Integer> userIds) {
		
		 Iterable<User> users = userRepository.findAll(userIds);
		 
		return StreamSupport.stream(users.spliterator(), false).map(u -> userMapper.getDtoFromUser(u) ).collect(Collectors.toList());
	}

	@Override
	public synchronized UserDto createUser(UserDto userDto) {
		
		User user = userMapper.getUserFromDto(userDto);
		
		ProfessionTypeDto professionTypeDto = professionTypeService.getProfessionType(userDto.getType());
		ProfessionType professionType = new ProfessionType();
		professionType.setId(professionTypeDto.getId());
		professionType.setName(professionTypeDto.getName());
		user.setProfessionType(professionType);
		
	    return userMapper.getDtoFromUser(userRepository.save(user));
	}
	
	@Override
	public synchronized UserDto updateUser(UserDto userDto) {
	    
		User user = userRepository.findOne(userDto.getId());
		if (user == null) {
			throw new NotFoundException("User not found, user id " + userDto.getId());
		}
		
		ProfessionType professionType = null;
		if (userDto.getType() != null && 
			!userDto.getType().equalsIgnoreCase(user.getProfessionType().getName())) {
			
			ProfessionTypeDto professionTypeDto = professionTypeService.getProfessionType(userDto.getType());
	       	
			professionType = new ProfessionType();
			professionType.setId(professionTypeDto.getId());
			professionType.setName(professionTypeDto.getName());
		}
		
		userMapper.updateUserFromDto(userDto, user);
		if (professionType != null) {
			user.setProfessionType(professionType);
		}
		
		return userMapper.getDtoFromUser(userRepository.save(user));
	}

}
