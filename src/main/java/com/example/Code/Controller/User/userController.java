package com.example.Code.Controller.User;

import com.example.Code.Entity.Payment.billPt;
import com.example.Code.Entity.User.user;
import com.example.Code.Model.userInfoResponse;
import com.example.Code.Service.PT.personal_trainerService;
import com.example.Code.Service.Payment.bill_ptService;
import com.example.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService ;

    @Autowired
    private bill_ptService bill_ptService;

    @Autowired
    private personal_trainerService personal_trainerService;

    @RequestMapping("/update")
    public String update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String address
    ){
        user user = userService.findById(id);
        user.getAccount().setEmail(email);
        user.getAccount().setPhone(phone);
        user.setAddress(address);
        user.setName(name);
        userService.save(user);
        return "successful";
    }

    @RequestMapping("/getUserByPT")
    public List<userInfoResponse> getUserByPT(@RequestParam("idPT") int idPT){
        List<userInfoResponse> res = new ArrayList<>();
        for (billPt bill:bill_ptService.getAll()) {
            if(bill.getPersonal_trainer().getId()== idPT && bill.getUser().getAccount().isEnable())
            {
                userInfoResponse userInfoResponse = new userInfoResponse(bill.getUser());
                res.add(userInfoResponse);
            }
        }
        return res ;
    }
}
