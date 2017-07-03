package com.medley.users.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import lombok.Data;

//@Data
@Table
@Entity(name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = -6912286649209357821L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="profession_type_id")
	private ProfessionType professionType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ProfessionType getProfessionType() {
		return professionType;
	}

	public void setProfessionType(ProfessionType professionType) {
		this.professionType = professionType;
	}
	
	
}
