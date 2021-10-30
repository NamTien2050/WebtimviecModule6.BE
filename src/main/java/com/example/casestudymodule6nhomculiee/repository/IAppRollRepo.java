package com.example.casestudymodule6nhomculiee.repository;
import com.example.casestudymodule6nhomculiee.model.User.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRollRepo extends JpaRepository<AppRole,Long> {
}
