package com.Code.Controller.Gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gymAdmin")
public class gymAdminController {
    @Autowired
    private com.Code.Service.Gym.gymService gymService ;

    @Autowired
    private com.Code.Service.Gym.comboService comboService;

    @RequestMapping("/enableAllGym")
    public String enableAllGym(){
        for (com.Code.Entity.Gym.gym gym:gymService.getAll()) {
            gym.setEnable(true);
            gymService.signNewGym(gym);
        }
        return "Successful";
    }

    @RequestMapping("/enableAllCombo")
    public String enableAllCombo(){
        for (com.Code.Entity.Gym.combo combo:comboService.getAll()) {
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
