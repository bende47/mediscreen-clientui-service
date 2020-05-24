package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mediscreen.beans.DeclencheursBean;
import com.mediscreen.beans.RapportBean;

@FeignClient(name = "microservice-rapports", url = "localhost:8083")
public interface RapportProxy {

	@GetMapping(value="addRapport/add/{idPatient}/{age}/{risques}")
	RapportBean addRapport(@PathVariable("idPatient") String idPatient,@PathVariable("age") int age,@PathVariable("risques") String risques);
	
	@GetMapping(value="rapport/all/{id}")
	List<RapportBean> all(@PathVariable("id") String id); 
	
	@GetMapping(value="declencheur/all")
	public List<DeclencheursBean> allDeclencheurs();
	
	@GetMapping(value="rapport/find/{id}")
	public RapportBean findRapport(@PathVariable("id") String id);
}
