package com.example.casestudymodule6nhomculiee.model.Entity;

import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private int experience;
    private String description;
    private String phone;
    private String image;
    private boolean status;

    @OneToOne
    @JoinColumn(name ="user_id")
    private AppUser appUser;






}
