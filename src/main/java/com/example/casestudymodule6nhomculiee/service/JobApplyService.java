package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.JobApply;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.repository.IJobApplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplyService {
    @Autowired
    IJobApplyRepo iJobApplyRepo;
    public void save(JobApply jobApply){
        iJobApplyRepo.save(jobApply);
    }
    public List<JobApply> findAll(){
        return iJobApplyRepo.findAll();
    }
    public List<JobApply> jobApplyListByPost(RecruitmentPost recruitmentPost,boolean status){
        return iJobApplyRepo.getJobAppliesByRecuitmentPostAndStatus(recruitmentPost,status);
    }
    public JobApply pickUserProfile(RecruitmentPost recruitmentPost, AppUser appUser){
        return iJobApplyRepo.pickUserProfile(recruitmentPost,appUser);
    }
    public List<JobApply> notify(AppUser appUser){
        return iJobApplyRepo.notify(appUser,true);
    }
}
