package com.example.Code.ServiceImpl.Auth;

import com.example.Code.Entity.Auth.token;
import com.example.Code.Repository.Auth.tokenRepository;
import com.example.Code.Service.Auth.tokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tokenServiceImpl implements tokenService {

    @Autowired
    private tokenRepository tokenRepository;
    @Override
    public token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void save(token signin_Token) {
        tokenRepository.save(signin_Token);
    }
}
