package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);

    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email);


}
