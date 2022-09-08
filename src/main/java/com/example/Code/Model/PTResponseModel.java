package com.example.Code.Model;

import com.example.Code.Entity.PT.personal_trainer;
import lombok.Data;

@Data
public class PTResponseModel {
    private int id ;
    private String username ;
    private String name ;
    private String address;
    private String phone ;
    private String email ;
    private String avatar;
    private int fee;
    private String Role ;
    private float rate = 5;
    private boolean enable ;

    private gymModel gym;

    public PTResponseModel(personal_trainer pt) {
        this.id = pt.getId();
        this.username = pt.getAccount().getUsername();
        this.name = pt.getName();
        this.address = pt.getAddress();
        this.phone = pt.getAccount().getPhone();
        this.email = pt.getAccount().getPhone();
        this.avatar = pt.getAvatar();
        this.fee = pt.getPrice();
        this.Role = pt.getAccount().getRole().getText();
        this.enable = pt.getAccount().isEnable();
        this.gym = new gymModel(pt.getGym());
    }
}
