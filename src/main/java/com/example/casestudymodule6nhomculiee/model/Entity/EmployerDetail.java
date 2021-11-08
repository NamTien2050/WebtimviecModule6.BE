package com.example.casestudymodule6nhomculiee.model.Entity;

import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EmployerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String image1;
    private String description;
    private String phone;
    private String googlemapLink;
    private String facebookLink;
    private int staffNumber;
    private String website;
    private String address;
    private int date; //năm thành lập
    private String location;
    private String email;
    private boolean status;
    private String field;

    @OneToOne
    @JoinColumn(name ="user_id")
    private AppUser appUser;

    @OneToMany(targetEntity = RecruitmentPost.class,cascade = CascadeType.ALL)
    private List<RecruitmentPost> recuitmentPostList;








}
