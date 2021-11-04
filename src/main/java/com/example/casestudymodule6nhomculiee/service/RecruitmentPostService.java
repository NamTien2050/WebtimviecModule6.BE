package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.repository.IRecruitmentPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RecruitmentPostService implements IRecruitmentPostService{
    @Autowired
    IRecruitmentPostRepo recruitmentPostRepo;
    @Override
    public Iterable<RecruitmentPost> findAll() {
        return recruitmentPostRepo.findAll();
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
    public Iterable<RecruitmentPost> findRecruitmentPostByAppUser_Id(Long id) {
        return recruitmentPostRepo.findRecruitmentPostByAppUser_Id(id);
    }

//    public Iterable<RecruitmentPost> showListById(Long id){
//        return recruitmentPostRepo.findRecruitmentPostByEmployerDetail_Id(id);
//    }



    //Đóng mở khóa bài đăng

}

