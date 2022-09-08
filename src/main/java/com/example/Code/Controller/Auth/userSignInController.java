package com.example.Code.Controller.Auth;

import com.example.Code.Entity.Auth.Account;
import com.example.Code.Entity.Auth.token;
import com.example.Code.Entity.User.user;
import com.example.Code.Model.*;
import com.example.Code.Service.Auth.AccountService;
import com.example.Code.Service.Auth.tokenService;
import com.example.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/signUser")
public class userSignInController {
    @Autowired
    private userService userService;

    @Autowired
    private AccountService AccountService;

    @Autowired
    private JavaMailSender javaMailSender ;

    @Autowired
    private tokenService tokenService;

    public void sendEmail(String toEmail ,
                          String subject,
                          String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ngaitrong0108@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }

    @RequestMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account(username,bCryptPasswordEncoder.encode(password),email,phone,false, role.USER,typeAccount.NORMAL);
        AccountService.save(account);
        user user = new user();
        user.setAccount(account);
        user.setAddress(address);
        user.setName(name);
        userService.save(user);
        return "Successful";
    }

    @PostMapping("/uploadAvatar")
    private String uploadAvatar(@RequestParam("username") String username,
                                @RequestParam("avatar") MultipartFile avatar){
        user user = userService.findByUserName(username);
        Uploader uploader = new Uploader();
        user.setAvatar(uploader.uploadFile(avatar));
        userService.save(user);
        return "Successful";
    }

    @RequestMapping("/sendToken")
    public String sendToken(@RequestParam("username") String username){
        Account account = AccountService.findByUsername(username);
        token token = new token();
        token.genNewToken();
        token.setTokenType(tokenType.REPASSWORD);
        token.setAccount(account);
        tokenService.save(token);
        sendEmail(account.getEmail(),"Token",token.getToken());
        return "Succesfull";
    }

    @RequestMapping("/confirmToken")
    public String confirmToken(@RequestParam("token") String tokenString, @RequestParam("username")String username){
        token token =tokenService.findByToken(tokenString);
        if(
                token.getAccount().getUsername().equals(username)&&
                        token.getExpiryAt().isAfter(LocalDateTime.now())
        )
        {
            Account acc = token.getAccount();
            acc.setEnable(true);
            AccountService.save(acc);
            return "Successfully";
        }
        else
            return "Failed";
    }

    @RequestMapping("/forgetPassword")
    public void forgetPassword (
            @RequestParam("username") String username,
            @RequestParam("newPassword") String newPassword
    ){
        Account account = AccountService.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setPassword(bCryptPasswordEncoder.encode(newPassword));
    }

    @RequestMapping("/changePassword")
    public void changePassword (
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword
    ) throws Exception{
        Account account = AccountService.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.encode(password).equals(account.getPassword()))
            account.setPassword(bCryptPasswordEncoder.encode(newPassword));
        else throw new Exception("Password not Match");
    }

    @RequestMapping("/createGoogleUser")
    public userInfoResponse createGoogleUser (
            @RequestParam("email") String email,
            @RequestParam("name")String name,
            @RequestParam("avatar")String avatar
    ){
        if(AccountService.findByUsername(email) == null) {
            Account account = new Account(email,"",email,"",true, role.USER, typeAccount.GOOGLE);
            AccountService.save(account);
            user user = new user();
            user.setAccount(account);
            user.setAddress("");
            user.setName(name);
            user.setAvatar(avatar);
            userService.save(user);
            userInfoResponse userInfoResponse = new userInfoResponse(user);
            return userInfoResponse;
        }
        else{
            Account accountResult = AccountService.findByUsername(email);
            user user =  userService.findByUserName(accountResult.getUsername());
            userInfoResponse userInfoResponse = new userInfoResponse(user);
            return userInfoResponse;
        }

    }

    @RequestMapping("/createFaceBookUser")
    public userInfoResponse createFaceBookUser (
            @RequestParam("email") String email,
            @RequestParam("name")String name,
            @RequestParam("avatar")String avatar
    ){
        if(AccountService.findByUsername(email) == null) {
            Account account = new Account(email,"",email,"",true, role.USER, typeAccount.FACEBOOK);
            AccountService.save(account);
            user user = new user();
            user.setAccount(account);
            user.setAddress("");
            user.setName(name);
            user.setAvatar(avatar);
            userService.save(user);
            userInfoResponse userInfoResponse = new userInfoResponse(user);
            return userInfoResponse;
        }
        else{
            Account accountResult = AccountService.findByUsername(email);
            user user =  userService.findByUserName(accountResult.getUsername());
            userInfoResponse userInfoResponse = new userInfoResponse(user);
            return userInfoResponse;
        }

    }


}
