package com.example.casestudymodule6nhomculiee.controller;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class RestAdminController {
    @Autowired
    EmploymentService employmentService;
    @GetMapping("/listEmploymentUnauthenticated")
    public ResponseEntity<?> showListEmploymentUnauthenticated(){
        List<EmployerDetail> list = employmentService.listEmploymentUnauthenticated(false);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED)  ;
    }
    @GetMapping("/deleteElementById/{id}")
    public ResponseEntity<?> deleteEmploymentById(@PathVariable Long id){
        employmentService.deleteEmploymentById(id);
        return new ResponseEntity<>( HttpStatus.ACCEPTED)  ;
    }
    @GetMapping("/acceptEmployment/{id}")
    public ResponseEntity<?> acceptEmployment(@PathVariable Long id){
        EmployerDetail employerDetail = employmentService.findEmploymentById(id);
        employerDetail.setStatus(true);
        employmentService.createEmployment(employerDetail);
        return new ResponseEntity<>( HttpStatus.ACCEPTED)  ;
    }



}
