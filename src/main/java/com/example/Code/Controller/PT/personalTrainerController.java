package com.example.Code.Controller.PT;

import com.example.Code.Entity.PT.personalTrainer;
import com.example.Code.Model.PTResponseModel;
import com.example.Code.Service.Auth.AccountService;
import com.example.Code.Service.PT.personal_trainerService;
import com.example.Code.Service.Payment.bill_ptService;
import com.example.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personal_trainer")
public class personalTrainerController {
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
        personalTrainer pt = personal_trainerService.findById(id);
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
