package com.medley.users.dtomapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.medley.users.UserDto;
import com.medley.users.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mappings({
        @Mapping(source="professionType.name", target="type")
    })
	UserDto getDtoFromUser(User userModel);
	
	@InheritInverseConfiguration
	User getUserFromDto(UserDto userDto);
	

	@Mappings({
		@Mapping(target="professionType.id", ignore=true),
		@Mapping(target="professionType.name", ignore=true)
	})
	void updateUserFromDto(UserDto userDto, @MappingTarget User user);
    
}
