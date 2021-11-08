package com.example.casestudymodule6nhomculiee.model.Entity;

import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class JobApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notify;
    private boolean status;
    private LocalDate date;

    @OneToOne
    @JoinColumn(name ="user_id")
    private AppUser appUser;

    @OneToOne
    @JoinColumn(name="requimentPost_id")
    private RecruitmentPost recuitmentPost;




}
