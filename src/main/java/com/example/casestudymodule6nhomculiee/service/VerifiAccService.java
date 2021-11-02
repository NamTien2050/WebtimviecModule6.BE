package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.User.VerifiAccount;
import com.example.casestudymodule6nhomculiee.repository.IVerifiAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifiAccService implements IVerifiAccService{
    @Autowired
    IVerifiAccount iVerifiAccount;
    public VerifiAccount add(VerifiAccount verifiAccount) {
        return iVerifiAccount.save(verifiAccount);
    }
    public Iterable<VerifiAccount> findAll() {
        return null;
    }

    public Optional<VerifiAccount> findById(Long id) {
        return iVerifiAccount.findById(id);
    }

}
