package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.service.RecruitmentPostService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecruitmentPostRepo extends JpaRepository<RecruitmentPost, Long> {

    Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id);
}
