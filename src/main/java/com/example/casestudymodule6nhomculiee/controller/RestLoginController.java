package com.example.casestudymodule6nhomculiee.controller;
import com.example.casestudymodule6nhomculiee.dto.RespondMessage;
import com.example.casestudymodule6nhomculiee.model.Entity.EmployerDetail;
import com.example.casestudymodule6nhomculiee.model.Entity.RecruitmentPost;
import com.example.casestudymodule6nhomculiee.model.Entity.UserProfile;
import com.example.casestudymodule6nhomculiee.model.User.AppRole;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.model.User.VerifiAccount;
import com.example.casestudymodule6nhomculiee.securityJWT.UserRespo;
import com.example.casestudymodule6nhomculiee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class RestLoginController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppUserService userService;
    @Autowired
    private AppRoleService roleService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    VerifiAccService verifiAccService;

    @Autowired
    EmploymentService employmentService;
    @Autowired
    UserProfileService userProfileService;

    @Autowired
    IRecruitmentPostService recruitmentPostService;





    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody AppUser user) {
        AppUser appUser = userService.loadUserByUsername(user.getUsername());

        Long id = appUser.getId();
        String role = appUser.getRoll().getName();
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if(userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUsername());
                UserRespo userRespo = new UserRespo(result,id,user.getUsername(),user.getAvatar(),role);
                httpStatus = HttpStatus.OK;
                return new ResponseEntity<>(userRespo,httpStatus);
            } else {
                result = "Wrong userId and password not verification";
                httpStatus = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(result,httpStatus);
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
//        UserRespo userRespo = new UserRespo(result,id,user.getUsername(),user.getAvatar(),role);
        return new ResponseEntity<>(result, httpStatus);


    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> Register(@RequestBody AppUser appUser){
        if(userService.existsByUsername(appUser.getUsername())){
            return new ResponseEntity<>(new RespondMessage("no_user"), HttpStatus.OK);
        }
        if(userService.existsByEmail(appUser.getEmail())){
            return new ResponseEntity<>(new RespondMessage("no_email"), HttpStatus.OK);
        }
        AppRole role = roleService.findById(3L).get();
        System.out.println(role.getName());
        appUser.setRoll(role);
        appUser.setStatus(false);
        userService.add(appUser);

        AppUser appUser1 = userService.loadUserByUsername(appUser.getUsername());
        String token = jwtService.generateTokenLogin(appUser.getUsername());
        VerifiAccount verifiAccount = new VerifiAccount();
        verifiAccount.setIdAcc(appUser1.getId());
        verifiAccount.setToken(token);
        VerifiAccount newVerifi = verifiAccService.add(verifiAccount);

        SimpleMailMessage sendmail = new SimpleMailMessage();
        sendmail.setTo(appUser.getEmail());
        sendmail.setSubject("Bấm vào link bên dưới để xác thực email!");
        sendmail.setText("http://localhost:8080/rest/verification/" + newVerifi.getId() + "/" + appUser.getId() + "?token=" + token);

        javaMailSender.send(sendmail);
        return new ResponseEntity<>(new RespondMessage("yes"),HttpStatus.OK);


    }
    @GetMapping("/verification/{id}/{idAcc}")
    public ResponseEntity<String> verification(@RequestParam("token") String token, @PathVariable("id") Long id, @PathVariable("idAcc") Long idAcc) {
        VerifiAccount verifiAccount = verifiAccService.findById(id).get();
        AppUser appUser = userService.findById(idAcc);
        if (verifiAccount.getToken().equals(token)) {
            appUser.setStatus(true);
            userService.addUser(appUser);
            return new ResponseEntity<>("Bấm vào link để đăng nhập https://localhost:8080/rest/login", HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @PostMapping("/createEmployment/{id}")
    public ResponseEntity<?> createEmployment(@RequestBody EmployerDetail employerDetail,@PathVariable Long id){
        AppUser appUser = userService.findById(id);
        employerDetail.setStatus(false);
        employerDetail.setAppUser(appUser);
        employmentService.createEmployment(employerDetail);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    @PostMapping("/createUserProfile/{id}")
    public ResponseEntity<?> createUserProfile(@RequestBody UserProfile userProfile,@PathVariable Long id){
        AppUser appUser = userService.findById(id);
        userProfile.setAppUser(appUser);
        userProfileService.createUserProfile(userProfile);
        SimpleMailMessage sendmail = new SimpleMailMessage();
        sendmail.setTo(appUser.getEmail());
        sendmail.setSubject("Bạn đã đăng kí tài khoản thành công, vui lòng chờ hệ thống xác nhận!");
        javaMailSender.send(sendmail);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("/UserProfileByUser/{id}")
    public ResponseEntity<?> getUserProfileByUser(@PathVariable Long id){
        AppUser appUser = userService.findById(id);
        UserProfile userProfile = userProfileService.getUserProfileByAppUser(appUser);
        return new ResponseEntity<>(userProfile,HttpStatus.OK);

    }
    @GetMapping("/UserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        AppUser appUser = userService.findById(id);
        return new ResponseEntity<>(appUser,HttpStatus.OK);

    }
    @GetMapping("/Employment/{id}")
    public ResponseEntity<?> getEmploymentById(@PathVariable Long id){
        EmployerDetail employerDetail = employmentService.findEmploymentById(id);
        return new ResponseEntity<>(employerDetail,HttpStatus.ACCEPTED);
    }
    @GetMapping("/EmploymentByUser/{id}")
    public ResponseEntity<?> getEmploymentByUser(@PathVariable Long id){
        AppUser appUser = userService.findById(id);
        EmployerDetail employerDetail = employmentService.getEmplementByUser(appUser);
        return new ResponseEntity<>(employerDetail,HttpStatus.ACCEPTED);
    }
    @GetMapping("/AllListPost")
    public ResponseEntity<?> showAllListPost(){
        Iterable<RecruitmentPost> recruitmentPostList= recruitmentPostService.findAll();
        return new ResponseEntity<>(recruitmentPostList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable Long id){
        UserProfile userProfile = userProfileService.findById(id);
        return new ResponseEntity<>(userProfile,HttpStatus.ACCEPTED);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<?> detailRecruitmentPost(@PathVariable Long id){
        Optional<RecruitmentPost> recruitmentPost = recruitmentPostService.findById(id);
        if(!recruitmentPost.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recruitmentPost.get(), HttpStatus.OK);
    }

    @GetMapping("/recruitmentPostPage")
    public ResponseEntity<?> pageRecruitmentPost(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<RecruitmentPost> recruitmentPostPage = recruitmentPostService.findAllPage(pageable);
        if(recruitmentPostPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recruitmentPostPage, HttpStatus.OK);
    }
}
