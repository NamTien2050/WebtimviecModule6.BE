package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRecruitmentPostService extends IGeneralService<RecruitmentPost> {
    Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id);
    List<RecruitmentPost> findAllBySalaryHot();
    List<RecruitmentPost> findAllByFiledHot();
    Page<RecruitmentPost> findAllPage(Pageable pageable);
    Page<RecruitmentPost> findByTitleAndLocationAndSalary(String t, String l, double min, Pageable pageable);

    Page<RecruitmentPost> findByTitleAndLocation(String t, String l, Pageable pageable);
    Page<RecruitmentPost> findByTitleAndSalary(String t, double salary, Pageable pageable);
    Page<RecruitmentPost> findByLocationAndSalary(String location, double salary, Pageable pageable);
    Page<RecruitmentPost> findAllByTitleContaining(String title, Pageable pageable);
    Page<RecruitmentPost> findAllByNameEmployerContaining(String nameEmployer, Pageable pageable);
    Page<RecruitmentPost> findAllByField(String field, Pageable pageable);
    Page<RecruitmentPost> searchAdvanced(Pageable pageable,String search);

    Page<RecruitmentPost> findAllByStatusIsTrue(Pageable pageable);

}
