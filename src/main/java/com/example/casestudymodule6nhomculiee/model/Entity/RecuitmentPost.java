package com.example.casestudymodule6nhomculiee.model.Entity;

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
public class RecuitmentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salary;
    private int quantity;
    private String gender;
    private String skill; // JAVA,PHP
    private String workType;
    private String requirement;
    private String experience;
    private String description;
    private LocalDate Date;
    private String field; //Lĩnh vực : tài chính, IT;
    private String agency; //List
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "employment_id")
    private EmployerDetail employerDetail;










}
