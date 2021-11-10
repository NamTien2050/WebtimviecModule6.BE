package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRecruitmentPostRepo extends JpaRepository<RecruitmentPost, Long> {
    @Query(nativeQuery = true, value = "SELECT *" +
            " FROM RecruitmentPost WHERE (:location IS NULL OR location LIKE %:location%)" +
            "AND (:field IS NULL OR field LIKE %:field%)")
    Iterable<RecruitmentPost> SearchHomePage(@Param("location") String location,
                                             @Param("field") String field);

    Iterable<RecruitmentPost> findAllByLocationContaining(String location);

    Iterable<RecruitmentPost> findAllByFieldContaining(String field);

    Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id);

    @Query("select a from RecruitmentPost a where a.status = ?1")
    List<RecruitmentPost> findAllByStatus(Boolean status);
    // Iterable<RecruitmentPost> findAllByAppUser(AppUser appUser);

    @Query("select r from RecruitmentPost r where lower(r.title) like lower(concat('%',:search,'%') ) " +
            "or lower(r.minSalary)like lower(concat('%',:search,'%') ) " +
            "or lower(r.expectation)like lower(concat('%',:search,'%') ) "+
            "or lower(r.location)like lower(concat('%',:search,'%') ) "
    )
    Page<RecruitmentPost> searchAdvanced(@Param("search") Pageable pageable, String search);

    @Query("select a from RecruitmentPost a where a.minSalary > ?1")
    List<RecruitmentPost> findAllBySalaryHot(double minSalary);

    @Query("select a from RecruitmentPost a where a.field = ?1 or a.field = ?2")
    List<RecruitmentPost> findAllByFieldHot(String field1, String field2);


    @Query("select a from RecruitmentPost a where a.title like %?1% and  a.location like %?2% and a.minSalary > ?3 and a.minSalary < ?3 +10 ")
    Page<RecruitmentPost> findRecruitmentPostByTitleAndLocationAndMinSalary(String t, String l, double n, Pageable pageable);


    // Iterable<RecruitmentPost> findAllByAppUser(AppUser appUser);
}
