package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediscreen.beans.PatientBean;

@FeignClient(name = "microservice-patients", url = "localhost:8081")
public interface PatientProxy {

	@PostMapping(value = "patient/add")
	PatientBean add(@RequestBody PatientBean patient);

	@GetMapping(value = "patient/find/{id}")
	PatientBean find(@PathVariable("id") String id);

	@GetMapping(value = "patient/all")
	List<PatientBean> all();

	@PutMapping(value = "patient/update/{id}")
	PatientBean update(@RequestBody PatientBean patient, @PathVariable("id") String id);

	@DeleteMapping(value = "patient/delete/{id}")
	void delete(@PathVariable("id") String id);
}
