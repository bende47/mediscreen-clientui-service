package com.mediscreen.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class HistoryBean {
	
	private String id;
	private String notes;
	private Date dateCreation;
	private Date dateUpdate;
	private String idPatient;
}
