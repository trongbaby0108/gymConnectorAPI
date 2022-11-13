package com.Code.Controller.Admin;

import com.Code.Entity.User.user;
import com.Code.Enum.role;
import com.Code.Exception.NotFoundException;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Service.User.userService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userAdmin")
public class userAdminController {
    @Autowired
    private userService userService ;

    @RequestMapping("/getUser")
    public ResponseEntity<?>getUser(){
        List<userInfoResponse> res = new ArrayList<>();
        userService.getAll().forEach(user -> {
            if (user.getAccount().getRole() == role.USER){
                userInfoResponse userInfoResponse = new userInfoResponse(user);
                res.add(userInfoResponse);
            }
        });
        return ResponseEntity.ok(res);
    }

    @SneakyThrows
    @RequestMapping("/disableUser")
    public HttpStatus disableUser(@RequestParam("idUser") int idUser){
        user user = userService.findById(idUser);
        if(user == null) throw new NotFoundException("user not found");
        user.getAccount().setEnable(false);
        userService.save(user);
        return HttpStatus.OK;
    }

    @SneakyThrows
    @RequestMapping("/enableUser")
    public HttpStatus enableUser(@RequestParam("idUser") int idUser){
        user user = userService.findById(idUser);
        if(user == null) throw new NotFoundException("user not found");
        user.getAccount().setEnable(true);
        userService.save(user);
        return HttpStatus.OK;
    }
}
