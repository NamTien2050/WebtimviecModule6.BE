package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.UserProfile;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.service.EmploymentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmploymentRepo extends JpaRepository<EmployerDetail,Long> {
    @Query("select a from EmployerDetail a where a.status = ?1")
    List<EmployerDetail> getEmployerDetailByStatus(Boolean status);

    @Query("select a from EmployerDetail a where a.appUser = ?1")
    EmployerDetail getEmployerDetailByByAppUser(AppUser appUser);

}
