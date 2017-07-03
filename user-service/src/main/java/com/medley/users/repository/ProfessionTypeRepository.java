package com.medley.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medley.users.model.ProfessionType;

@Repository
public interface ProfessionTypeRepository extends CrudRepository<ProfessionType, Integer> {
	
	ProfessionType findByName(String type);

}
