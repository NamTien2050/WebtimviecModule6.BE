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
    Page<RecruitmentPost> findByTitleAndLocationAndSalary(String t, String l, double s, Pageable pageable);
}
