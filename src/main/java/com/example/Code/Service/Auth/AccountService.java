package com.example.Code.Service.Auth;

import com.example.Code.Entity.Auth.Account;

import java.util.List;


public interface AccountService {
    public List<Account> getAll();
    public Account findByUsername(String username);

    public void save(Account account);
}
