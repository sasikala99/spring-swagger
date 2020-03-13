package com.springconsumer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value="/loadconsumer")
	public Map<String,Object> loadDoctorDetails(){
		Map<String,Object>  resultMapObj = new HashMap<String,Object>();
		try {
			String id = restTemplate.getForObject("http://spring-service/loadDoctorDetails", String.class);
			resultMapObj.put("doctorDetailsListObj", id);
		} catch(Exception e) {
			e.printStackTrace();
			resultMapObj.put("error", e.getMessage());
		}
		return resultMapObj;
	}

}
