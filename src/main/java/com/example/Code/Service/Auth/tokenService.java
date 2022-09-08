package com.example.Code.Service.Auth;

import com.example.Code.Entity.Auth.token;

public interface tokenService {
    public token findByToken(String token);
    public void save(token signin_Token);
}
