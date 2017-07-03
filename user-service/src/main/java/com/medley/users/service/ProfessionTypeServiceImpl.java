package com.medley.users.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medley.users.ProfessionTypeDto;
import com.medley.users.dtomapper.ProfessionTypeMapper;
import com.medley.users.exceptions.NotFoundException;
import com.medley.users.model.ProfessionType;
import com.medley.users.repository.ProfessionTypeRepository;

@Service
public class ProfessionTypeServiceImpl implements ProfessionTypeService {
	
	@Resource
	private ProfessionTypeRepository professionTypeRepository;
	
	@Resource
	private ProfessionTypeMapper professionTypeMapper;

	@Override
	public List<ProfessionTypeDto> getProfessionTypes() {
		
		Iterable<ProfessionType> professionTypes = professionTypeRepository.findAll();
		
		return StreamSupport.stream(professionTypes.spliterator(), false).
			   map(p -> professionTypeMapper.fromProfessionType(p) ).
			   collect(Collectors.toList());
	}

	@Override
	public ProfessionTypeDto getProfessionType(String type) {
		
		ProfessionType professionType = professionTypeRepository.findByName(type);
		if (professionType == null) throw new NotFoundException("Profession type not found " + type);
		
		return professionTypeMapper.fromProfessionType(professionType);
	}
}
