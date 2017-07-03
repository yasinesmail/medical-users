package com.medley.users;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 5111058068100957477L;

	private Integer id;
	
	private String firstName;
	
	private String lastName;

	private String type;
}
