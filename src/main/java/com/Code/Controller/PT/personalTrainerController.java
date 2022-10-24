package com.Code.Controller.PT;

import com.Code.Entity.PT.personalTrainer;
import com.Code.Model.PTResponseModel;
import com.Code.Service.PT.personalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personal_trainer")
public class personalTrainerController {
    @Autowired
    private personalTrainerService personal_trainerService;

    @RequestMapping("/update")
    public PTResponseModel update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam int price) {
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
