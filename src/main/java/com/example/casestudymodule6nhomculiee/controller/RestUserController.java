package com.example.casestudymodule6nhomculiee.controller;

import com.example.casestudymodule6nhomculiee.dto.RespondMessage;
import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.JobApply;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.service.AppUserService;
import com.example.casestudymodule6nhomculiee.service.IRecruitmentPostService;
import com.example.casestudymodule6nhomculiee.service.JobApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class RestUserController {
    @Autowired
    IRecruitmentPostService recruitmentPostService;
    @Autowired
    AppUserService appUserService;
    @Autowired
    JobApplyService jobApplyService;

    @GetMapping("/recruiment/{id_user}/{id_post}")
    public ResponseEntity<?> showListEmploymentAuthenticated(@PathVariable Long id_user,@PathVariable Long id_post){
        AppUser appUser = appUserService.findById(id_user);
        RecruitmentPost recruitmentPost = recruitmentPostService.findById(id_post).get();
        JobApply jobApply1 = jobApplyService.pickUserProfile(recruitmentPost,appUser);
        if(jobApply1 !=null){
            return new ResponseEntity<>(jobApply1,HttpStatus.ACCEPTED);
        }
        JobApply jobApply = new JobApply();
        jobApply.setAppUser(appUser);
        jobApply.setRecuitmentPost(recruitmentPost);
        jobApply.setStatus(false);
        jobApplyService.save(jobApply);

        return new ResponseEntity<>( HttpStatus.ACCEPTED)  ;
    }
    @GetMapping("/notify/{id}")
    public ResponseEntity<?> notify(@PathVariable Long id){
        AppUser appUser = appUserService.findById(id);
        List<JobApply> list = jobApplyService.notify(appUser);
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED)  ;
    }

    @GetMapping("/save/{id}")
    public ResponseEntity<?> Save(@PathVariable Long id){
        AppUser appUser = appUserService.findById(id);
        List<RecruitmentPost> recruitmentPosts = new ArrayList<>();
        List<JobApply> list = jobApplyService.save(appUser);
        for(int i =0;i<list.size();i++){
            recruitmentPosts.add(list.get(i).getRecuitmentPost());
        }
        return new ResponseEntity<>(recruitmentPosts,HttpStatus.ACCEPTED);
    }


}
