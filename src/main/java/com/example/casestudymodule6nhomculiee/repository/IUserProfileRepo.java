package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.UserProfile;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserProfileRepo extends JpaRepository<UserProfile,Long> {
    @Query("select a from UserProfile a where a.appUser = ?1")
    UserProfile getUserProfileByAppUser(AppUser appUser);
}
