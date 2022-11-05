package com.Code.Controller.User;

import com.Code.Entity.Payment.billPt;
import com.Code.Entity.User.user;
import com.Code.Model.Request.updateUserRequest;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Service.Payment.billPtService;
import com.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private billPtService bill_ptService;

    @PostMapping("/update")
    public String update(@RequestBody updateUserRequest updateUserRequest) {
        user user = userService.findById(updateUserRequest.getId());
        user.getAccount().setEmail(updateUserRequest.getEmail());
        user.getAccount().setPhone(updateUserRequest.getPhone());
        user.setAddress(updateUserRequest.getAddress());
        user.setName(updateUserRequest.getName());
        userService.save(user);
        return "successful";
    }

    @RequestMapping("/getUserByPT")
    public List<userInfoResponse> getUserByPT(@RequestParam("idPT") int idPT) {
        List<userInfoResponse> res = new ArrayList<>();
        for (billPt bill : bill_ptService.getAll()) {
            if (bill.getPersonal_trainer().getId() == idPT && bill.getUser().getAccount().isEnable()) {
                userInfoResponse userInfoResponse = new userInfoResponse(bill.getUser());
                res.add(userInfoResponse);
            }
        }
        return res;
    }
}
