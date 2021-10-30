package com.example.casestudymodule6nhomculiee.controller;
import com.example.casestudymodule6nhomculiee.dto.ResponMessage;
import com.example.casestudymodule6nhomculiee.model.User.AppRole;
import com.example.casestudymodule6nhomculiee.model.User.AppUser;
import com.example.casestudymodule6nhomculiee.securityJWT.UserRespo;
import com.example.casestudymodule6nhomculiee.service.AppRoleService;
import com.example.casestudymodule6nhomculiee.service.AppUserService;
import com.example.casestudymodule6nhomculiee.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

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


    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRespo> login(HttpServletRequest request, @RequestBody AppUser user) {
        AppUser appUser = userService.loadUserByUsername(user.getUsername());

        Long id = appUser.getId();
        String role = appUser.getRoll().getName();
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if(userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        UserRespo userRespo = new UserRespo(result,id,user.getUsername(),user.getAvatar(),role);
        return new ResponseEntity<UserRespo>(userRespo, httpStatus);


    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> Register(@RequestBody AppUser appUser){
        if(userService.existsByUsername(appUser.getUsername())){
            return new ResponseEntity<>(new ResponMessage("no_user"), HttpStatus.OK);
        }
        if(userService.existsByEmail(appUser.getEmail())){
            return new ResponseEntity<>(new ResponMessage("no_email"), HttpStatus.OK);
        }
        AppRole role = roleService.findById(3L).get();
        System.out.println(role.getName());
        appUser.setRoll(role);
        userService.add(appUser);
        SimpleMailMessage sendmail = new SimpleMailMessage();
        sendmail.setTo(appUser.getEmail());
        sendmail.setSubject("Bấm vào link bên dưới để xác thực email!");
     //   sendmail.setText("https://vilo-vn.herokuapp.com/account/verification/" + newVerifi.getId() + "/" + acc.getId() + "?token=" + token);
        javaMailSender.send(sendmail);
        return new ResponseEntity<>("yes",HttpStatus.OK);
    }






}
