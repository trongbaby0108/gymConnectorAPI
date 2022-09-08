package com.example.Code.Model;

import com.example.Code.Entity.Gym.gym;
import lombok.Data;

@Data
public class gymModel {
    private int id ;
    private String name ;
    private String address ;
    private String phone ;
    private String email ;
    private String avatar;
    private float rate;

    public gymModel(gym gym) {
        this.id = gym.getId();
        this.name = gym.getName();
        this.address = gym.getAddress();
        this.phone = gym.getPhone();
        this.email = gym.getEmail();
        this.avatar = gym.getAvatar();
    }



}
