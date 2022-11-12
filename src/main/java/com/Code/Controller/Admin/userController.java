package com.Code.Controller.Admin;

import com.Code.Entity.Payment.billPt;
import com.Code.Entity.User.user;
import com.Code.Exception.NotFoundException;
import com.Code.Model.Request.updateUserRequest;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Service.Payment.billPtService;
import com.Code.Service.User.userService;
import lombok.SneakyThrows;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private billPtService billPtService;

    @SneakyThrows
    @PostMapping("/update")
    public HttpStatus update(@RequestBody updateUserRequest updateUserRequest) {
        user user = userService.findById(updateUserRequest.getId());
        if(user == null) throw new NotFoundException("user not found");
        user.getAccount().setEmail(updateUserRequest.getEmail());
        user.getAccount().setPhone(updateUserRequest.getPhone());
        user.setAddress(updateUserRequest.getAddress());
        user.setName(updateUserRequest.getName());
        userService.save(user);
        return HttpStatus.OK;
    }

    @RequestMapping("/getUserByPT")
    public ResponseEntity<?> getUserByPT(@RequestParam("idPT") int idPT) {
        List<userInfoResponse> res = new ArrayList<>();
        billPtService.getAll().forEach(bill -> {
            if (bill.getPersonal_trainer().getId() == idPT && bill.getUser().getAccount().isEnable()) {
                userInfoResponse userInfoResponse = new userInfoResponse(bill.getUser());
                res.add(userInfoResponse);
            }
        });
        if(res.size() != 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(res);
    }
}
