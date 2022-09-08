package com.example.Code.Controller.Payment;

import com.example.Code.Entity.PT.personal_trainer;
import com.example.Code.Entity.Payment.billPt;
import com.example.Code.Entity.User.user;
import com.example.Code.Model.billPTResponse;
import com.example.Code.Service.PT.personal_trainerService;
import com.example.Code.Service.Payment.bill_ptService;
import com.example.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bill_pt")
public class bill_ptController {
    @Autowired
    private bill_ptService bill_ptService;

    @Autowired
    private userService userService;


    @Autowired
    private personal_trainerService personal_trainerService ;

    @RequestMapping("checkout")
    public Boolean checkout(
            @RequestParam("idUser") int idUser,
            @RequestParam("idPt") int idPt){
        user user = userService.findById(idUser);
        personal_trainer pt = personal_trainerService.findById(idPt);
        if(bill_ptService.getByUser(idUser) != null){
            return false;
        }
        billPt bill= new billPt();
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
