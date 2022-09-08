package com.example.Code.Repository.Auth;

import com.example.Code.Entity.Auth.token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tokenRepository extends JpaRepository<token,Integer> {
    public token findByToken(String token);
}
