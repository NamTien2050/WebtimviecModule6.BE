package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.User.AppRole;
import com.example.casestudymodule6nhomculiee.repository.IAppRollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppRoleService  implements IAppRoleService{
    @Autowired
    IAppRollRepo appRoleRepository;
    @Override
    public Iterable<AppRole> findAll() {
        return null;
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return appRoleRepository.findById(id);
    }

    @Override
    public void save(AppRole appRole) {

    }

    @Override
    public void remove(Long id) {

    }

}
