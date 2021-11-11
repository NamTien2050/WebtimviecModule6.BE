package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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



    @Query("select a from RecruitmentPost a where a.title like %?1% and  a.location like %?2% and a.minSalary > ?3 and a.minSalary  < ?3 +5 ")
    Page<RecruitmentPost> findRecruitmentPostByTitleAndLocationAndMinSalary(String t,String l, double min,Pageable pageable);

    @Query("select a from RecruitmentPost a where a.title like %?1% and  a.location like %?2% ")
    Page<RecruitmentPost> findAllByTitleAndLocationContaining(String title,String location, Pageable pageable);

    @Query("select a from RecruitmentPost a where a.title like %?1% and  a.minSalary < ?2 and a.minSalary +5 > ?2 ")
    Page<RecruitmentPost> findAllByTitleAndMinSalaryContaining(String title,double min, Pageable pageable);


    @Query("select a from RecruitmentPost a where a.location like %?1% and  a.minSalary < ?2 and a.minSalary +5 > ?2 ")
    Page<RecruitmentPost> findAllByLocationAndMinSalaryContaining(String title,double min, Pageable pageable);


    @Query("select a from RecruitmentPost a where a.title like %?1%  ")
    Page<RecruitmentPost> findAllByTitleContaining(String title, Pageable pageable);

    @Query("select a from RecruitmentPost a where a.location like %?1%  ")
    Page<RecruitmentPost> findAllByLocationContaining(String location, Pageable pageable);


    @Query("select a from RecruitmentPost a where a.minSalary < ?2 and a.minSalary +5 > ?2  ")
    Page<RecruitmentPost> findAllBySalaryContaining(double salary, Pageable pageable);

    @Query("select a from RecruitmentPost a where a.nameEmployer like %?1%   ")
    Page<RecruitmentPost> findAllByNameEmployerContaining(String nameEmployer, Pageable pageable);

    Page<RecruitmentPost> findAllByField(String field, Pageable pageable);


    @Query("select r from RecruitmentPost r where lower(r.title) like lower(concat('%',:search,'%') ) " +
            "or lower(r.minSalary)like lower(concat('%',:search,'%') ) " +
            "or lower(r.maxSalary)like lower(concat('%',:search,'%') ) " +
            "or lower(r.skill)like lower(concat('%',:search,'%') ) " +
            "or lower(r.field)like lower(concat('%',:search,'%') ) " +
            "or lower(r.location)like lower(concat('%',:search,'%') ) " +
            "or lower(r.nameEmployer)like lower(concat('%',:search,'%') ) "+
            "or lower(r.location)like lower(concat('%',:search,'%') ) "
    )
    Page<RecruitmentPost> searchAdvanced(@Param("search") Pageable pageable, String search);

    Page<RecruitmentPost> findAllByStatusIsTrue(Pageable pageable);
    ;








    // Iterable<RecruitmentPost> findAllByAppUser(AppUser appUser);
}
