package com.medley.users.validation;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.medley.users.ProfessionTypeDto;
import com.medley.users.UserDto;
import com.medley.users.exceptions.NotFoundException;
import com.medley.users.model.User;
import com.medley.users.repository.UserRepository;
import com.medley.users.service.ProfessionTypeService;
import com.mysql.cj.core.util.StringUtils;

import lombok.Data;

@Data
@Component
public class UserServiceValidation {
	
	@Resource
	private ProfessionTypeService professionTypeService;
	
	@Resource
	private UserRepository userRepository;
	
	private void validateProfessionType(String professionType) {
				
        String lcType = professionType.toLowerCase();
        
        try {
		    ProfessionTypeDto professionTypeDto = professionTypeService.getProfessionType(lcType);
        }
        catch (NotFoundException e) {
        	throw new IllegalArgumentException(e.getLocalizedMessage());
        }
        return;
	}
	
	
	public void validateCreateDto(UserDto userDto) {
		
		if (userDto.getId() != null) {
			throw new IllegalArgumentException("User id found, use put to update");
		}
		
		String fName = userDto.getFirstName();
		if (StringUtils.isNullOrEmpty(fName)) {
			throw new IllegalArgumentException("First name not specified");
		}
		
		String lName = userDto.getLastName();
		if (StringUtils.isNullOrEmpty(lName)) {
			throw new IllegalArgumentException("Last name not specified");
		}
		
		String type = userDto.getType();
		if (StringUtils.isNullOrEmpty(type)) {
			throw new IllegalArgumentException("Type name not specified");
		}
		
		validateProfessionType(type);
		
		return;
		
	}
	
	public void validateUpdateDto(UserDto userDto) {
		
		Integer userId = userDto.getId();
		if (userId == null) {
			throw new IllegalArgumentException("User id not specified");
		}
		
		String fName = userDto.getFirstName();
		if (StringUtils.isEmptyOrWhitespaceOnly(fName)) {
			throw new IllegalArgumentException("First name not specified");
		}
		
		String lName = userDto.getLastName();
		if (StringUtils.isEmptyOrWhitespaceOnly(lName)) {
			throw new IllegalArgumentException("Last name not specified");
		}
		
		String type = userDto.getType();
		if (StringUtils.isEmptyOrWhitespaceOnly(type)) {
			throw new IllegalArgumentException("Type name not specified");
		}
		
		validateProfessionType(type);
		
		return;
	}
	

}
