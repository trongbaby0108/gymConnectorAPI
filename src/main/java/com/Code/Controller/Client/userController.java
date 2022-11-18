package com.Code.Controller.Client;

import com.Code.Entity.User.user;
import com.Code.Exception.NotFoundException;
import com.Code.Model.Request.updateUserRequest;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Service.Payment.billPtService;
import com.Code.Service.User.userService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client/user")
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private billPtService billPtService;

    @SneakyThrows
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody updateUserRequest updateUserRequest) {
        user user = userService.findById(updateUserRequest.getId());
        if(user == null) throw new NotFoundException("user not found");
        user.getAccount().setEmail(updateUserRequest.getEmail());
        user.getAccount().setPhone(updateUserRequest.getPhone());
        user.setAddress(updateUserRequest.getAddress());
        user.setName(updateUserRequest.getName());
        userService.save(user);
        return ResponseEntity.ok(new userInfoResponse(user));
    }


}
