package com.example.casestudymodule6nhomculiee.controller;

import com.example.casestudymodule6nhomculiee.dto.ChangeStatus;
import com.example.casestudymodule6nhomculiee.dto.RespondMessage;
import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.JobApply;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.Entity.UserProfile;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employers/recruitmentPosts")
public class RestEmploymentController {
    @Autowired
    IRecruitmentPostService recruitmentPostService;
    @Autowired
    AppUserService appUserService;
    @Autowired
    JobApplyService jobApplyService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    EmploymentService employmentService;
//    @GetMapping
//    public ResponseEntity<?> pageCategory(@PageableDefault(sort = "nameCategory", direction = Sort.Direction.ASC) Pageable pageable){
//        Page<Category> categoryPage = categoryService.findAll(pageable);
//        if(categoryPage.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
//    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> RecruitmentPostList(@PathVariable Long id){

        Iterable<RecruitmentPost> recruitmentPostList= recruitmentPostService.findRecruitmentPostByAppUser_Id(id);
        return new ResponseEntity<>(recruitmentPostList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createRecruitmentPost( @RequestBody RecruitmentPost recruitmentPost){
        Long id = recruitmentPost.getAppUser().getId();
        AppUser appUser = appUserService.findById(id);
        EmployerDetail employerDetail = employmentService.findEmployerByUserId(appUser);
        recruitmentPost.setLogo(employerDetail.getLogo());
        recruitmentPost.setNameEmployer(employerDetail.getName());
        recruitmentPostService.save(recruitmentPost);
        return new ResponseEntity<>(new RespondMessage("create_success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruitmentPost(@PathVariable Long id){
        Optional<RecruitmentPost> recruitmentPost = recruitmentPostService.findById(id);
        if(!recruitmentPost.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recruitmentPostService.remove(id);
        return new ResponseEntity<>(new RespondMessage("delete_success"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecruitmentPost(@PathVariable Long id, @RequestBody RecruitmentPost recruitmentPost){


        Optional<RecruitmentPost> recruitmentPost1 = recruitmentPostService.findById(id);

        recruitmentPost1.get().setTitle(recruitmentPost.getTitle());
        recruitmentPost1.get().setMinSalary(recruitmentPost.getMinSalary());
        recruitmentPost1.get().setMaxSalary(recruitmentPost.getMaxSalary());
        recruitmentPost1.get().setQuantity(recruitmentPost.getQuantity());
        recruitmentPost1.get().setGender(recruitmentPost.getGender());
        recruitmentPost1.get().setSkill(recruitmentPost.getSkill());
        recruitmentPost1.get().setWorkType(recruitmentPost.getWorkType());
        recruitmentPost1.get().setPosition(recruitmentPost.getPosition());
        recruitmentPost1.get().setExperience(recruitmentPost.getExperience());
        recruitmentPost1.get().setPosition(recruitmentPost.getPosition());
        recruitmentPost1.get().setExperience(recruitmentPost.getExperience());
        recruitmentPost1.get().setDate(recruitmentPost.getDate());
        recruitmentPost1.get().setField(recruitmentPost.getField());
        recruitmentPost1.get().setLocation(recruitmentPost.getLocation());
        recruitmentPostService.save(recruitmentPost1.get());
        return new ResponseEntity<>(new RespondMessage("update_success"), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailRecruitmentPost(@PathVariable Long id){
        Optional<RecruitmentPost> recruitmentPost = recruitmentPostService.findById(id);
        if(!recruitmentPost.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recruitmentPost.get(), HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<?> updateRecruitmentPostStatus (@PathVariable Long id , @RequestBody ChangeStatus changeStatus){
        Optional<RecruitmentPost> recruitmentPost1 = recruitmentPostService.findById(id);
        if (changeStatus.isStatus()){
            recruitmentPost1.get().setStatus(true);
        }
        else {
            recruitmentPost1.get().setStatus(false);
        }
        recruitmentPostService.save(recruitmentPost1.get());
        return new ResponseEntity<>(recruitmentPost1.get().isStatus(),HttpStatus.OK);
    }
    @GetMapping("/UserProfileofEmployment/{id}")
    public ResponseEntity<?> UserProfileOfEmployment(@PathVariable Long id){
        RecruitmentPost recruitmentPost = recruitmentPostService.findById(id).get();
        List<JobApply> jobApplyList = jobApplyService.jobApplyListByPost(recruitmentPost,false);
        List<AppUser> appUsers = new ArrayList<>();
        List<UserProfile> userProfiles = new ArrayList<>();
        for (int i =0; i<jobApplyList.size();i++){
            if(jobApplyList.get(i).getAppUser()!=null)
            appUsers.add(jobApplyList.get(i).getAppUser());
        }
        for (int i=0;i<appUsers.size();i++){
           UserProfile userProfile = userProfileService.getUserProfileByAppUser(appUsers.get(i));
           userProfiles.add(userProfile);
        }

        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }
    @GetMapping("/pickUserProfile/{id_user}/{id_post}")
    public ResponseEntity<?> PickUserProfile(@PathVariable Long id_user,@PathVariable Long id_post){
        AppUser appUser = appUserService.findById(id_user);
        RecruitmentPost recruitmentPost = recruitmentPostService.findById(id_post).get();
        JobApply jobApply = jobApplyService.pickUserProfile(recruitmentPost,appUser);
        jobApply.setStatus(true);
        jobApply.setDate(LocalDate.now());
        jobApply.setNotify("Hồ sơ của bạn đã được chọn, vui lòng tới công ty"+"" +recruitmentPost.getNameEmployer()+" tại địa chỉ" + "" +recruitmentPost.getLocation()+"để phỏng vấn");
        jobApplyService.save(jobApply);
        return new ResponseEntity<>("thành công", HttpStatus.OK);


    }

}
