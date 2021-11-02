package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.repository.IEmploymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentService {
     @Autowired
     IEmploymentRepo iEmploymentRepo;
     public void createEmployment(EmployerDetail employerDetail){
         iEmploymentRepo.save(employerDetail);
     }

}
