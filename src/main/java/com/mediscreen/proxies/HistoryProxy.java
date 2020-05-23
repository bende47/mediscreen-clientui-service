package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediscreen.beans.HistoryBean;

@FeignClient(name = "microservice-history", url = "localhost:8082")
public interface HistoryProxy {

	@PostMapping(value = "patHistory/add")
	HistoryBean add(@RequestBody HistoryBean history);

	@PutMapping(value = "patHistory/update/{id}")
	HistoryBean update(@RequestBody HistoryBean history, @PathVariable("id") String id);

	@GetMapping(value = "patHistory/all")
	List<HistoryBean> all();

	@GetMapping(value = "patHistory/allHistoryPatient/{idpatient}")
	List<HistoryBean> allHistoryPatient(@PathVariable("idpatient") String idpatient);
	
	@GetMapping(value="patHistory/find/{id}")
	HistoryBean find(@PathVariable("id") String id);
}
