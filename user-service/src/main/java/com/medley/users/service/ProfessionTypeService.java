package com.medley.users.service;

import java.util.List;

import com.medley.users.ProfessionTypeDto;

public interface ProfessionTypeService {
	
	public List<ProfessionTypeDto> getProfessionTypes();
	
	public ProfessionTypeDto getProfessionType(String type);
}
