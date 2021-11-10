package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecruitmentPostRepo  extends JpaRepository<RecruitmentPost, Long> {
    Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id);
    @Query("select a from RecruitmentPost a where a.status = ?1")
    List<RecruitmentPost> findAllByStatus(Boolean status);

   // Iterable<RecruitmentPost> findAllByAppUser(AppUser appUser);
   Page<RecruitmentPost> findAllByField(String field, Pageable pageable);

   Page<RecruitmentPost> findAllByLocation(String location, Pageable pageable);
}
