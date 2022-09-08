package com.example.Code.Controller.Gym;

import com.example.Code.Entity.Gym.gym;
import com.example.Code.Model.Uploader;
import com.example.Code.Service.Gym.gymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class gymController {
    @Autowired
    private gymService _gymService;

    @GetMapping("/getAll")
    public List<gym> getAll(){
        return _gymService.getAll();
    }

    @PostMapping("/addGym")
    public String addGym(
                           @RequestParam("email") String email ,
                           @RequestParam("address") String address ,
                           @RequestParam("name") String name ,
                           @RequestParam("phone") String phone ){
        gym gym = new gym();

        gym.setAddress(address);
        gym.setEmail(email);
        gym.setName(name);
        gym.setPhone(phone);

        _gymService.signNewGym(gym);
        return "OK";
    }

    @PostMapping("/updateGym")
    public String updateGym(
            @RequestParam("id") int id,
            @RequestParam("email") String email ,
            @RequestParam("address") String address ,
            @RequestParam("name") String name ,
            @RequestParam("phone") String phone ){
        gym gym = _gymService.findGymById(id);
        gym.setAddress(address);
        gym.setEmail(email);
        gym.setName(name);
        gym.setPhone(phone);

        _gymService.signNewGym(gym);
        return "OK";
    }

    @PostMapping("/addGymImg")
    public String addGymImg(
            @RequestParam("img") MultipartFile img,
            @RequestParam("name") String name
    ){
        Uploader uploader = new Uploader();
        gym gym = _gymService.findGymByName(name);
        if(gym != null){
            gym.setAvatar(uploader.uploadFile(img));
            _gymService.signNewGym(gym);
            return "OK";
        }
        else return "Fail";
    }
}
