package com.springservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springservice.persistance.Doctordetails;

@Repository
public interface DoctorRepository extends JpaRepository<Doctordetails, String> {

}
