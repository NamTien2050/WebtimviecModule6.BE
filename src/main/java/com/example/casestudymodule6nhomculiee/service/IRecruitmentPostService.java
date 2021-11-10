package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRecruitmentPostService extends IGeneralService<RecruitmentPost> {
    Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id);
    Page<RecruitmentPost> findAllPage(Pageable pageable);
    Page<RecruitmentPost> findAllByField(String field, Pageable pageable);
    Page<RecruitmentPost> findALlByLocation(String location, Pageable pageable);
}
