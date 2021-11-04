package com.example.casestudymodule6nhomculiee.service;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;

public interface IRecruitmentPostService extends IGeneralService<RecruitmentPost> {
    Iterable<RecruitmentPost> SearchHomePage(String location, String field);

    Iterable<RecruitmentPost>findAllByLocationContaining(String location);
    Iterable<RecruitmentPost>findAllByFieldContaining(String field);
}
