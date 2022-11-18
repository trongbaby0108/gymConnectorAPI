package com.Code.Controller.Client;

import com.Code.Entity.PT.personalTrainer;
import com.Code.Model.Request.ptUpdateRequest;
import com.Code.Model.Response.PTResponse;
import com.Code.Model.Response.userInfoResponse;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.Payment.billPtService;
import com.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client/personalTrainer")
public class personalTrainerController {
    @Autowired
    private personalTrainerService personalTrainerService;

    @Autowired
    private userService userService;

    @Autowired
    private billPtService billPtService;

    @RequestMapping("/update")
    public PTResponse update(@RequestBody ptUpdateRequest ptUpdateRequest) {
        personalTrainer pt = personalTrainerService.findById(ptUpdateRequest.getId());
        pt.getAccount().setEmail(ptUpdateRequest.getEmail());
        pt.getAccount().setPhone(ptUpdateRequest.getPhone());
        pt.setAddress(ptUpdateRequest.getAddress());
        pt.setName(ptUpdateRequest.getName());
        pt.setPrice(ptUpdateRequest.getPrice());
        personalTrainerService.save(pt);
        return new PTResponse(pt);
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
        if(res.size() == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(res);
    }
}
