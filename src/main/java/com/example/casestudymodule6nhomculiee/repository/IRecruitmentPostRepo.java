package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecruitmentPostRepo  extends JpaRepository<RecruitmentPost, Long> {
    Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id);

    @Query("select a from RecruitmentPost a where a.status = ?1")
    List<RecruitmentPost> findAllByStatus(Boolean status);

    @Query("select a from RecruitmentPost a where a.minSalary > ?1")
    List<RecruitmentPost> findAllBySalaryHot(double minSalary);

    @Query("select a from RecruitmentPost a where a.field = ?1 or a.field = ?2")
    List<RecruitmentPost> findAllByFieldHot(String field1,String field2);

   // Iterable<RecruitmentPost> findAllByAppUser(AppUser appUser);
}
