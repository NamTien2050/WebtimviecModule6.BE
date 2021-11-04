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
public class RecruitmentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double minSalary;
    private double maxSalary;
    private int quantity;
    private String gender;
    private String skill; // JAVA,PHP
    private String workType;
    private String position;
    private String experience;
    private String description;
    private LocalDate date;
    private String field; //Lĩnh vực : tài chính, IT;
    private String location; //List
    private boolean status;

    //        @ManyToOne
//    @JoinColumn(name = "employment_id")
//    private EmployerDetail employerDetail;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;


}
