package com.example.casestudymodule6nhomculiee.model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class JobApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;

    @OneToOne
    @JoinColumn(name ="userProfile_id")
    private UserProfile userProfile;

    @OneToOne
    @JoinColumn(name="requimentPost_id")
    private RecruitmentPost recuitmentPost;




}
