package com.Code.Controller.Client;


import com.Code.Entity.Gym.combo;
import com.Code.Entity.Gym.gym;
import com.Code.Entity.Payment.billGym;
import com.Code.Entity.User.user;
import com.Code.Model.Response.billGymResponse;
import com.Code.Service.Gym.comboService;
import com.Code.Service.Gym.gymService;
import com.Code.Service.Payment.billGymService;
import com.Code.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("client/billGym")
public class billGymController {
    @Autowired
    private billGymService bill_gymService;

    @Autowired
    private userService userService;

    @Autowired
    private gymService gymService;

    @Autowired
    private comboService comboService;

    @RequestMapping("/checkout")
    public Boolean checkout(
            @RequestParam("idUser") int idUser,
            @RequestParam("idGym") int idGym,
            @RequestParam("idCombo") int idCombo) {
        user user = userService.findById(idUser);
        gym gym = gymService.findGymById(idGym);
        combo combo = comboService.findByid(idCombo);
        if (bill_gymService.getByUser(idUser) != null) {
            System.out.println(bill_gymService.getByUser(idUser).toString());
            return false;
        }
        billGym bill = new billGym();
        bill.setUser(user);
        bill.setGym(gym);
        bill.setCombo(combo);
        bill.setDayStart(LocalDateTime.now());
        bill.setDayEnd(LocalDateTime.now().plusMonths(1));
        bill_gymService.save(bill);
        return true;
    }

    @RequestMapping("/checkGymExit")
    public billGymResponse checkGymExit(@RequestParam("idUser") int idUser) {
        billGymResponse billGymResponse = new billGymResponse();
        billGym bill = bill_gymService.getByUser(idUser);
        if (bill != null) {
            billGymResponse.setId(bill.getId());
            billGymResponse.setDayStart(bill.getDayStart().toString());
            billGymResponse.setDayEnd(bill.getDayEnd().toString());
            billGymResponse.setGym(bill.getGym());
            billGymResponse.setCombo(bill.getCombo());
            return billGymResponse;
        }
        return null;
    }
}
