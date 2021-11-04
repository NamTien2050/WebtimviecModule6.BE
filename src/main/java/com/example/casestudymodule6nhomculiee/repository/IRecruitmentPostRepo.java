package com.example.casestudymodule6nhomculiee.repository;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRecruitmentPostRepo extends JpaRepository<RecruitmentPost, Long> {
    @Query(nativeQuery = true, value = "SELECT *" +
            " FROM RecruitmentPost WHERE (:location IS NULL OR location LIKE %:location%)" +
            "AND (:field IS NULL OR field LIKE %:field%)")
    Iterable<RecruitmentPost> SearchHomePage(@Param("location") String location,
                                             @Param("field") String field);

    Iterable<RecruitmentPost> findAllByLocationContaining(String location);
    Iterable<RecruitmentPost>findAllByFieldContaining(String field);
}
