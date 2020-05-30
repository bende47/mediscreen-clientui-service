package com.mediscreen.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.beans.DeclencheursBean;
import com.mediscreen.beans.HistoryBean;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.beans.RapportBean;
import com.mediscreen.proxies.HistoryProxy;
import com.mediscreen.proxies.PatientProxy;
import com.mediscreen.proxies.RapportProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
public class ClientUIController {

	@Autowired
	PatientProxy patienProxy;
	
	@Autowired
	HistoryProxy historyProxy;
	
	@Autowired
	RapportProxy rapportProxy;
	
	@PostMapping("/patient/add")
	public PatientBean add(@RequestBody PatientBean patient) {
		return patienProxy.add(patient);
	}

	@GetMapping("/patient/all")
	public List<PatientBean> all() {
		return patienProxy.all();
	}

	@PutMapping(value = "patient/update/{id}")
	public PatientBean update(@RequestBody PatientBean patient, @PathVariable("id") String id) {
		return patienProxy.update(patient, id);
	}
	
	@GetMapping(value = "patient/find/{id}")
	public PatientBean find(@PathVariable("id") String id) {
		return patienProxy.find(id);
	}
	
	@GetMapping(value = "patHistory/allHistoryPatient/{idpatient}")
	public List<HistoryBean> allHistoryPatient(@PathVariable("idpatient") String idpatient){
		return historyProxy.allHistoryPatient(idpatient);
	}
	
	@PostMapping(value = "patHistory/add")
	public HistoryBean add(@RequestBody HistoryBean history) {
		return historyProxy.add(history);
	}
	
	@PutMapping(value = "patHistory/update/{id}")
	HistoryBean update(@RequestBody HistoryBean history, @PathVariable("id") String id) {
		return historyProxy.update(history, id);
	}
	
	@GetMapping(value="patHistory/find/{id}")
	public HistoryBean findHistory(@PathVariable("id") String id) {
		return historyProxy.find(id);
	}
	
	@GetMapping(value = "patHistory/all")
	public List<HistoryBean> allHistory(){
		return historyProxy.all();
	}
	
	@PostMapping(value = "rapport/stat")
	public RapportBean rapport(@RequestBody PatientBean patient)  {		

		LocalDate localDate = patient.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();		
		int age = determineAge(localDate);
		
		List<HistoryBean> listHistoriquePatient = new ArrayList<HistoryBean>();
		
		listHistoriquePatient = historyProxy.allHistoryPatient(patient.getId());
		
		List<DeclencheursBean> listDecl = new ArrayList<DeclencheursBean>();
		listDecl = rapportProxy.allDeclencheurs();
		
		int cpt = 0;
		
		for (HistoryBean histBean : listHistoriquePatient) {			
			for (DeclencheursBean decl : listDecl) {
				 if(histBean.getNotes().contains(decl.getLibelle())) {
					 cpt++;
				 }
			}			
		}
		log.info("Compte = " +cpt);

		String risque = "Aucun risque";
		
		
		if(age > 30) {
			
			if(cpt==2) {
				risque = "Risque limite";	
			}
			
			if(cpt==6) {
				risque = "Danger";
			}
			
			if(cpt>= 8) {
				risque = "Apparition precoce";
			}
			
		}else {
			if("M".equals(patient.getSex())) {
				if(cpt==3) {
					risque = "Danger";
				}
				if(cpt==5) {
					risque = "Apparition precoce";
				}
			}else {
				if(cpt==4) {
					risque = "Danger";
				}
				if(cpt==7) {
					risque = "Apparition precoce";
				}
			}
		}
		
		if(!risque.equals("")) {
			rapportProxy.addRapport(patient.getId(),age,risque);
		}
		

		
		log.info("Age =" +age);
		log.info("Risque =" + risque);
		log.info("Patient = " +patient.getId());
		return rapportProxy.findRapport(patient.getId());
	}
	
	
	@SuppressWarnings("static-access")
	public int determineAge(LocalDate date) {
		 Calendar calendar = Calendar.getInstance();

		int annee=date.getYear();
		int calannee=calendar.get(Calendar.YEAR)-annee;
		
		int mois=date.getMonthValue();
		int calmois=calendar.MONTH - mois;			

		if (calmois >= 0) {
			return calannee;
		} else {
			return calannee - 1;
		}
	}
	
}
