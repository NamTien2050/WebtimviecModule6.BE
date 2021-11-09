package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.repository.IRecruitmentPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruitmentPostService implements IRecruitmentPostService {
    @Autowired
    IRecruitmentPostRepo recruitmentPostRepo;

    @Override
    public Iterable<RecruitmentPost> findAll() {
        return recruitmentPostRepo.findAllByStatus(true);
    }

    @Override
    public Iterable<RecruitmentPost> showAllPost() {
        return recruitmentPostRepo.findAll();
    }

    @Override
    public Iterable<RecruitmentPost> searchAdvanced(String field, Integer minSalary, String jobName, String location) {
        return recruitmentPostRepo.searchAdvanced(field, minSalary, jobName, location);
    }

    @Override
    public Optional<RecruitmentPost> findById(Long id) {
        return recruitmentPostRepo.findById(id);
    }

    @Override
    public void save(RecruitmentPost recruitmentPost) {
        recruitmentPostRepo.save(recruitmentPost);
    }

    @Override
    public void remove(Long id) {
        recruitmentPostRepo.deleteById(id);
    }

    @Override
    public Iterable<RecruitmentPost> SearchHomePage(String location, String field) {
        return recruitmentPostRepo.SearchHomePage(location, field);
    }

    @Override
    public Iterable<RecruitmentPost> findAllByLocationContaining(String location) {
        return recruitmentPostRepo.findAllByLocationContaining(location);
    }

    @Override
    public Iterable<RecruitmentPost> findAllByFieldContaining(String field) {
        return recruitmentPostRepo.findAllByFieldContaining(field);
    }
    //Đóng mở khóa bài đăng

    public Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id) {
        return recruitmentPostRepo.findRecruitmentPostByAppUser_Id(id);
    }
}


