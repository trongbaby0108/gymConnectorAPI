package com.Code.Controller.Payment;

import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.Payment.billPt;
import com.Code.Entity.User.user;
import com.Code.Model.billPTResponse;
import com.Code.Service.PT.personalTrainerService;
import com.Code.Service.Payment.billPtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bill_pt")
public class billPtController {
    @Autowired
    private billPtService bill_ptService;

    @Autowired
    private com.Code.Service.User.userService userService;


    @Autowired
    private personalTrainerService personal_trainerService;

    @RequestMapping("checkout")
    public Boolean checkout(
            @RequestParam("idUser") int idUser,
            @RequestParam("idPt") int idPt) {
        user user = userService.findById(idUser);
        personalTrainer pt = personal_trainerService.findById(idPt);
        if (bill_ptService.getByUser(idUser) != null) {
            return false;
        }
        billPt bill = new billPt();
        bill.setUser(user);
        bill.setPersonal_trainer(pt);
        bill.setDayStart(LocalDateTime.now());
        bill.setDayEnd(LocalDateTime.now().plusMonths(1));
        bill_ptService.save(bill);
        return true;
    }

    @RequestMapping("checkPTExit")
    public billPTResponse checkGymExit(@RequestParam("idUser") int idUser) {
        billPTResponse billPTResponse = new billPTResponse();
        billPt bill = bill_ptService.getByUser(idUser);
        if (bill != null) {
            billPTResponse.setId(bill.getId());
            billPTResponse.setTrainer(bill.getPersonal_trainer());
            billPTResponse.setDayStart(bill.getDayStart());
            billPTResponse.setDayEnd(bill.getDayEnd());
            return billPTResponse;
        }
        return billPTResponse;
    }
}
