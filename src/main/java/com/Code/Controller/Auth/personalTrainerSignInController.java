package com.Code.Controller.Auth;

import com.Code.Entity.Auth.Account;
import com.Code.Entity.Auth.token;
import com.Code.Entity.PT.personalTrainer;
import com.Code.Model.Uploader;
import com.Code.Model.role;
import com.Code.Model.tokenType;
import com.Code.Model.typeAccount;
import com.Code.Service.PT.personalTrainerService;
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
@RequestMapping("/signInPersonalTrainer")
public class personalTrainerSignInController {
    @Autowired
    private personalTrainerService personalTrainerService;

    @Autowired
    private com.Code.Service.Auth.AccountService AccountService;

    @Autowired
    private com.Code.Service.Gym.gymService gymService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private com.Code.Service.Auth.tokenService tokenService;

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

    @RequestMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("gym") int gymID,
            @RequestParam("price") int price
    ) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account(
                username,
                bCryptPasswordEncoder.encode(password),
                email,
                phone,
                false,
                role.PERSONAL_TRAINER,
                typeAccount.NORMAL);
        AccountService.save(account);
        personalTrainer personal_trainer = new personalTrainer();
        personal_trainer.setAccount(account);
        personal_trainer.setAddress(address);
        personal_trainer.setName(name);
        personal_trainer.setGym(gymService.findGymById(gymID));
        personal_trainer.setPrice(price);
        personalTrainerService.save(personal_trainer);
        return "Successful";
    }

    @PostMapping("/uploadAvatar")
    private String uploadAvatar(@RequestParam("username") String username,
                                @RequestParam("avatar") MultipartFile avatar) {
        personalTrainer personal_trainer = personalTrainerService.findByUsername(username);
        Uploader uploader = new Uploader();
        personal_trainer.setAvatar(uploader.uploadFile(avatar));
        personalTrainerService.save(personal_trainer);
        return "Successful";
    }

    @RequestMapping("/sendToken")
    public void sendToken(@RequestParam("username") String username) {
        Account account = AccountService.findByUsername(username);
        token token = new token();
        token.genNewToken();
        token.setTokenType(tokenType.REPASSWORD);
        token.setAccount(account);
        tokenService.save(token);
        sendEmail(account.getEmail(), "Token", token.getToken());
    }

    @RequestMapping("/confirmToken")
    public String confirmToken(@RequestParam("token") String tokenString,
                               @RequestParam("username") String username) {
        token token = tokenService.findByToken(tokenString);
        if (
                token.getAccount().getUsername().equals(username) &&
                        token.getExpiryAt().isAfter(LocalDateTime.now())
        ) {
            Account acc = token.getAccount();
            acc.setEnable(true);
            AccountService.save(acc);
            return "Successfully";
        } else
            return "Failed";
    }

    @RequestMapping("/forgetPassword")
    public void forgetPassword(
            @RequestParam("username") String username,
            @RequestParam("newPassword") String newPassword
    ) {
        Account account = AccountService.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setPassword(bCryptPasswordEncoder.encode(newPassword));
    }

    @RequestMapping("/changePassword")
    public void changePassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword
    ) throws Exception {
        Account account = AccountService.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(password, account.getPassword())) {
            account.setPassword(bCryptPasswordEncoder.encode(newPassword));
            AccountService.save(account);
        } else throw new Exception("Password not Match");
    }
}
