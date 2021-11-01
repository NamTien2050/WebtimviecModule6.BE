package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecuitmentPost;
import com.example.casestudymodule6nhomculiee.repository.IRecuitmentPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RecuitmentPostService implements IRecuitmentPostService{
    @Autowired
    IRecuitmentPostRepo recuitmentPostRepo;
    @Override
    public Iterable<RecuitmentPost> findAll() {
        return recuitmentPostRepo.findAll();
    }

    @Override
    public Optional<RecuitmentPost> findById(Long id) {
        return recuitmentPostRepo.findById(id);
    }

    @Override
    public void save(RecuitmentPost recuitmentPost) {
        recuitmentPostRepo.save(recuitmentPost);
    }

    @Override
    public void remove(Long id) {
        recuitmentPostRepo.deleteById(id);
    }
}
