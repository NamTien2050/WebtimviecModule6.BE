package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.UserProfile;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.repository.IUserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements IUserProfileService{
    @Autowired
    IUserProfileRepo iUserProfileRepo;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        iUserProfileRepo.save(userProfile);
    }

    @Override
    public UserProfile getUserProfileByAppUser(AppUser appUser){
       return iUserProfileRepo.getUserProfileByAppUser(appUser);
    }
}
