package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.UserProfile;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;

public interface IUserProfileService {
    void createUserProfile(UserProfile userProfile);
    UserProfile getUserProfileByAppUser(AppUser appUser);
}
