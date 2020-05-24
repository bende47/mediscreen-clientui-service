package com.mediscreen.beans;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RapportBean {
	
	private String id;
	private String idPatient;
	private int age;
	private String risque;
	private Date dateCreation;
}
