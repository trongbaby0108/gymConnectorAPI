package com.example.Code.Controller.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Code.Entity.User.user;
import com.example.Code.Model.userInfoResponse;
import com.example.Code.Service.User.userService;

@RestController
@RequestMapping("/userAdmin")
public class userAdminController {
    @Autowired
    private userService userService ;

    @RequestMapping("/getUser")
    public List<userInfoResponse> getUser(){
        List<userInfoResponse> res = new ArrayList<>();
        for (user user : userService.getAll()) {
            if (user.getAccount().getRole().getText() == "USER"){
                userInfoResponse userInfoResponse = new userInfoResponse(user);
                res.add(userInfoResponse);
            }
        }
        return res ;
    }

    @RequestMapping("/disableUser")
    public String disableUser(@RequestParam("idUser") int idUser){
        user user = userService.findById(idUser);
        user.getAccount().setEnable(false);
        userService.save(user);
        return "Successful";
    }

    @RequestMapping("/enableUser")
    public String enableUser(@RequestParam("idUser") int idUser){
        user user = userService.findById(idUser);
        user.getAccount().setEnable(true);
        userService.save(user);
        return "Successful";
    }
}
