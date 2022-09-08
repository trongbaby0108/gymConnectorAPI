package com.example.Code.Controller.Gym;

import com.example.Code.Entity.Gym.combo;
import com.example.Code.Entity.Gym.gym;
import com.example.Code.Service.Gym.comboService;
import com.example.Code.Service.Gym.gymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gymAdmin")
public class gymAdminController {
    @Autowired
    private gymService gymService ;

    @Autowired
    private comboService comboService;

    @RequestMapping("/enableAllGym")
    public String enableAllGym(){
        for (gym gym:gymService.getAll()) {
            gym.setEnable(true);
            gymService.signNewGym(gym);
        }
        return "Successful";
    }

    @RequestMapping("/enableAllCombo")
    public String enableAllCombo(){
        for (combo combo:comboService.getAll()) {
            combo.setEnable(true);
            comboService.save(combo);
        }
        return "Successful";
    }


    @RequestMapping("/disableGym")
    public String disableGym(){
        return "Successful";
    }

    @RequestMapping("/enableGym")
    public String enableGym(){
        return "Successful";
    }

    @RequestMapping("/disableCombo")
    public String disableCombo(){
        return "Successful";
    }

    @RequestMapping("/enableCombo")
    public String enableCombo(){
        return "Successful";
    }
}
