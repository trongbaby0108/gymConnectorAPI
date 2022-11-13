package com.Code.Controller.Client;

import com.Code.Entity.PT.personalTrainer;
import com.Code.Model.Request.ptUpdateRequest;
import com.Code.Model.Response.PTResponse;
import com.Code.Service.PT.personalTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/personalTrainer")
public class personalTrainerController {
    @Autowired
    private personalTrainerService personalTrainerService;

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
}
