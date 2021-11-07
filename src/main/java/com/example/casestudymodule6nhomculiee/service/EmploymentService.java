package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.repository.IEmploymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentService {
    @Autowired
    IEmploymentRepo iEmploymentRepo;

    public void createEmployment(EmployerDetail employerDetail) {
        iEmploymentRepo.save(employerDetail);
    }

    public List<EmployerDetail> listEmploymentUnauthenticated(Boolean status) {
        return iEmploymentRepo.getEmployerDetailByStatus(status);
    }

    public void deleteEmploymentById(Long id) {
        iEmploymentRepo.findById(id);
    }

    public EmployerDetail findEmploymentById(Long id) {
        return iEmploymentRepo.findById(id).get();
    }

    public EmployerDetail getEmplementByUser(AppUser appUser) {
        return iEmploymentRepo.getEmployerDetailByByAppUser(appUser);
    }

}
