package com.example.casestudymodule6nhomculiee.service;


import com.example.casestudymodule6nhomculiee.model.User.AppUser;

public interface IAppUserService extends IGeneralService<AppUser> {
    AppUser getUserByUsername(String username);

    AppUser getCurrentUser();

    Iterable<AppUser> findAppUserByRole();
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
}
