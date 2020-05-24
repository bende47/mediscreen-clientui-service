package com.mediscreen.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class DeclencheursBean {
	private String id;
	private String libelle;
	private Date dateCreation;	
}
