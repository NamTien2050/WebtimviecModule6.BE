package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmploymentRepo extends JpaRepository<EmployerDetail,Long> {
}
