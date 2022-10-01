package com.Code.Controller.Gym;


import com.Code.Entity.Gym.discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class discountController {
    @Autowired
    private com.Code.Service.Gym.discountService discountService ;
    @GetMapping("/getAll")
    private List<discount> getAll(){
        return discountService.getAll();
    }
    @PostMapping("/save")
    private void save(@RequestBody discount discount) {
        discountService.save(discount);
    }

    @GetMapping("/getByGym")
    private List<discount> getByGym(@RequestParam int id) {
        return discountService.getByGym(id);
    }
}
