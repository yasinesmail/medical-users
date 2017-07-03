package com.medley.users.dtomapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.medley.users.ProfessionTypeDto;
import com.medley.users.model.ProfessionType;

@Mapper(componentModel = "spring")
public interface ProfessionTypeMapper {
	
	ProfessionTypeDto fromProfessionType(ProfessionType professionTypeModel);
	
	@InheritInverseConfiguration
	ProfessionType toProfessionType(ProfessionTypeDto professionTypeDto);

}
