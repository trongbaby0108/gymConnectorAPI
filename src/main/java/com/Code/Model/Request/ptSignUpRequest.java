package com.Code.Model.Request;

import lombok.Data;

@Data
public class ptSignUpRequest {
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private int gymID;
    private int price;
}
