package com.medley.users;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProfessionTypeDto implements Serializable {
	
	private static final long serialVersionUID = 2351005313373330641L;

	private Integer id;
	
	private String name;
}
