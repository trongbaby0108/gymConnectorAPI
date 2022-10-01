package com.Code.Controller.Gym;

import com.Code.Entity.Gym.combo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combo")
public class comboController {
    @Autowired
    private com.Code.Service.Gym.comboService comboService;

    @Autowired
    private com.Code.Service.Gym.gymService gymService;

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
