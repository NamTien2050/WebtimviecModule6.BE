package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.repository.IRecruitmentPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RecruitmentPostService implements IRecruitmentPostService{
    @Autowired
    IRecruitmentPostRepo recruitmentPostRepo;
    @Override
    public Iterable<RecruitmentPost> findAll() {
        return recruitmentPostRepo.findAllByStatus(true);
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
    @Override
    public List<RecruitmentPost> findAllBySalaryHot(){
        return recruitmentPostRepo.findAllBySalaryHot(15);
    }
    @Override
    public List<RecruitmentPost> findAllByFiledHot(){
        return recruitmentPostRepo.findAllByFieldHot("IT","Tài chính");
    }

}
