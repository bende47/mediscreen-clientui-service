package com.mediscreen.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class PatientBean {
	
	private String id;
	private String name;
	private String firstname;
	private String sex;
	private Date birthday;
	private String address;
	private String phone;
	private Date dateCreate;
	private Date dateUpdate;
}
