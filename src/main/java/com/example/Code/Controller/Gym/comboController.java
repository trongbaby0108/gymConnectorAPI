package com.example.Code.Controller.Gym;

import com.example.Code.Entity.Gym.combo;
import com.example.Code.Service.Gym.comboService;
import com.example.Code.Service.Gym.gymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.List;

@RestController
@RequestMapping("/combo")
public class comboController {
    @Autowired
    private comboService comboService;

    @Autowired
    private gymService gymService;

    @PostMapping("addCombo")
    public String addCombo(
            @RequestParam("name") String name ,
            @RequestParam("price") int price,
            @RequestParam("gymId") int gymId
    ){
        combo combo = new combo();
        combo.setName(name);
        combo.setPrice(price);
        combo.setGym(gymService.findGymById(gymId));
        comboService.save(combo);
        return "Success";
    }

    @PostMapping("updateCombo")
    public String updateCombo(
            @RequestParam("id") int id,
            @RequestParam("name") String name ,
            @RequestParam("price") int price
    ){
        combo combo = comboService.findByid(id);
        combo.setName(name);
        combo.setPrice(price);
        comboService.save(combo);
        return "Success";
    }
}
