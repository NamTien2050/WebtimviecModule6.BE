package com.example.casestudymodule6nhomculiee.controller;

import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.service.IRecruitmentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post", method = RequestMethod.GET)
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IRecruitmentPostService iRecruitmentPostService;

    @GetMapping
    public ResponseEntity<Iterable<RecruitmentPost>> findAll() {
        List<RecruitmentPost> posts = (List<RecruitmentPost>) iRecruitmentPostService.findAll();
        if (posts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(iRecruitmentPostService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/searchHomePage")
    public ResponseEntity<Iterable<RecruitmentPost>> SearchHomePage(String location, String field) {
        List<RecruitmentPost> findByLoFi = (List<RecruitmentPost>) iRecruitmentPostService.SearchHomePage(location, field);
        if (findByLoFi.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(findByLoFi, HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity<Iterable<RecruitmentPost>> findAllByLocationContaining(@RequestParam String location) {
        Iterable<RecruitmentPost> locations = iRecruitmentPostService.findAllByLocationContaining(location);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/fields/{field}")
    public ResponseEntity<Iterable<RecruitmentPost>> findAllByFieldContaining(@PathVariable String field) {
        List<RecruitmentPost> fields = (List<RecruitmentPost>) iRecruitmentPostService.findAllByFieldContaining(field);
        if (fields.isEmpty())
            return new ResponseEntity<>(fields, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/searchAdvanced")
    public ResponseEntity<Page<RecruitmentPost>> searchAdvanced(@RequestParam(name = "search") String search, Pageable pageable) {
        Page<RecruitmentPost> postList = iRecruitmentPostService.searchAdvanced(pageable, search);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
//@PostMapping
//    public ResponseEntity<Iterable<RecruitmentPost>> createPost(){
//}
}
