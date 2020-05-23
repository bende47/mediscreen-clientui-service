package com.mediscreen.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.beans.HistoryBean;
import com.mediscreen.beans.PatientBean;
import com.mediscreen.proxies.HistoryProxy;
import com.mediscreen.proxies.PatientProxy;

@CrossOrigin("*")
@RestController
public class ClientUIController {

	@Autowired
	PatientProxy patienProxy;
	
	@Autowired
	HistoryProxy historyProxy;
	
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
	public String rapport(@RequestBody PatientBean patient)  {		
		System.out.println(patient);
		return "";
	}
	
	
}
