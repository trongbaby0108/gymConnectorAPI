package com.example.Code.Repository.Auth;

import com.example.Code.Entity.Auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    public Account findByUsername(String username);
}
