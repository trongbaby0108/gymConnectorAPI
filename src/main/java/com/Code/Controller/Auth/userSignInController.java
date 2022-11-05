package com.Code.Controller.Auth;

import com.Code.Entity.Auth.Account;
import com.Code.Entity.Auth.token;
import com.Code.Entity.User.user;
import com.Code.Enum.role;
import com.Code.Enum.tokenType;
import com.Code.Enum.typeAccount;
import com.Code.Model.Request.changePasswordRequest;
import com.Code.Model.Request.userSignUpRequest;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Service.Auth.AccountService;
import com.Code.Service.Auth.tokenService;
import com.Code.Service.User.userService;
import com.Code.Util.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
    private JavaMailSender javaMailSender;

    @Autowired
    private tokenService tokenService;

    public void sendEmail(String toEmail,
            String subject,
            String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ngaitrong0108@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }

    @PostMapping("/save")
    public String save(@RequestBody userSignUpRequest userSignUpRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account(
                userSignUpRequest.getUsername(),
                bCryptPasswordEncoder.encode(userSignUpRequest.getPassword()),
                userSignUpRequest.getEmail(),
                userSignUpRequest.getPhone(),
                false,
                role.USER,
                typeAccount.NORMAL);
        AccountService.save(account);
        user user = new user();
        user.setAccount(account);
        user.setAddress(userSignUpRequest.getAddress());
        user.setName(userSignUpRequest.getName());
        userService.save(user);
        return "Successful";
    }

    @PostMapping("/uploadAvatar")
    private String uploadAvatar(@RequestParam("username") String username,
            @RequestParam("avatar") MultipartFile avatar) {
        user user = userService.findByUserName(username);
        Uploader uploader = new Uploader();
        user.setAvatar(uploader.uploadFile(avatar));
        userService.save(user);
        return "Successful";
    }

    @RequestMapping("/sendToken")
    public String sendToken(@RequestParam("username") String username) {
        Account account = AccountService.findByUsername(username);
        token token = new token();
        token.genNewToken();
        token.setTokenType(tokenType.REPASSWORD);
        token.setAccount(account);
        tokenService.save(token);
        sendEmail(account.getEmail(), "Token", token.getToken());
        return "Succesfull";
    }

    @RequestMapping("/confirmToken")
    public String confirmToken(@RequestParam("token") String tokenString, @RequestParam("username") String username) {
        if(tokenService.confirmToken(username,tokenString))
            return "Successfully";
        else
            return "Failed";
    }

    @RequestMapping("/forgetPassword")
    public void forgetPassword(
            @RequestParam("username") String username,
            @RequestParam("newPassword") String newPassword) {
        Account account = AccountService.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setPassword(bCryptPasswordEncoder.encode(newPassword));
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody changePasswordRequest changePasswordRequest) throws Exception {
        Account account = AccountService.findByUsername(changePasswordRequest.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.encode(changePasswordRequest.getPassword()).equals(account.getPassword()))
            account.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getPassword()));
        else
            throw new Exception("Password not Match");
    }

    @RequestMapping("/createGoogleUser")
    public userInfoResponse createGoogleUser(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("avatar") String avatar) {
        if (AccountService.findByUsername(email) == null) {
            Account account = new Account(email, "", email, "", true, role.USER, typeAccount.GOOGLE);
            AccountService.save(account);
            user user = new user();
            user.setAccount(account);
            user.setAddress("");
            user.setName(name);
            user.setAvatar(avatar);
            userService.save(user);
            return new userInfoResponse(user);
        } else {
            Account accountResult = AccountService.findByUsername(email);
            user user = userService.findByUserName(accountResult.getUsername());
            return new userInfoResponse(user);
        }
    }
}
