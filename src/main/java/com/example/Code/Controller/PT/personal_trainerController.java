package com.example.Code.Controller.PT;

import com.example.Code.Entity.Auth.Account;
import com.example.Code.Entity.PT.personal_trainer;
import com.example.Code.Entity.User.user;
import com.example.Code.Model.PTResponseModel;
import com.example.Code.Model.Uploader;
import com.example.Code.Model.role;
import com.example.Code.Service.Auth.AccountService;
import com.example.Code.Service.PT.personal_trainerService;
import com.example.Code.Service.Payment.bill_ptService;
import com.example.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

@RestController
@RequestMapping("/personal_trainer")
public class personal_trainerController {
    @Autowired
    private personal_trainerService personal_trainerService;

    @Autowired
    private AccountService AccountService;

    @Autowired
    private userService userService ;

    @Autowired
    private bill_ptService bill_ptService;


    @RequestMapping("/update")
    public PTResponseModel update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam int price
    ){
        personal_trainer pt = personal_trainerService.findById(id);
        pt.getAccount().setEmail(email);
        pt.getAccount().setPhone(phone);
        pt.setAddress(address);
        pt.setName(name);
        pt.setPrice(price);
        personal_trainerService.save(pt);
        PTResponseModel ptResponseModel = new PTResponseModel(pt);
        return ptResponseModel;
    }
}
