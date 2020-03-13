package com.springservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springservice.persistance.Doctordetails;
import com.springservice.repository.DoctorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Doctor Management System", description = "Operations pertaining to doctor in Doctor Management System")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;
	
	 @ApiOperation(value = "Add an Doctor Details")
	@PostMapping(value="/saveDoctorDetails" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> saveDoctorDetails(
			@ApiParam(value = "Employee object store in database table", required = true) @RequestBody Doctordetails doctorDetails){
		Map<String,Object> resultMapObj = new HashMap<String,Object>();
		try {			
			doctorRepository.save(doctorDetails);
			resultMapObj.put("doctorDetailsListObj", doctorRepository.findAll());
		} catch(Exception e) {
			e.printStackTrace();
			resultMapObj.put("error", e.getMessage());
		}		
		return resultMapObj;
	}
	
    @ApiOperation(value = "View a list of available doctors", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@GetMapping(value="/loadDoctorDetails" , produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> loadDoctorDetails(){
		Map<String,Object> resultMapObj = new HashMap<String,Object>();
		try {
			resultMapObj.put("doctorDetailsListObj", doctorRepository.findAll());
		} catch(Exception e) {
			e.printStackTrace();
			resultMapObj.put("status", e.getMessage());
		}
		return resultMapObj;
	}
	
    @ApiOperation(value = "Get an Doctor by Id")
	@GetMapping(value="/loadDoctorDetailsBasedOnDoctorid/{doctorid}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> loadDoctorDetailsBasedOnDoctorid(
			@ApiParam(value = "Doctor id from which Doctor object will retrieve", required = true) @PathVariable("doctorid") String doctorid){
		Map<String,Object> resultMapObj = new HashMap<String,Object>();
		try {
			resultMapObj.put("doctorDetailsListObj", doctorRepository.findById(doctorid));
		} catch(Exception e) {
			e.printStackTrace();
			resultMapObj.put("status", e.getMessage());
		}
		return resultMapObj;
	}
}
