package com.example.Code.ServiceImpl.Auth;

import com.example.Code.Entity.Auth.Account;
import com.example.Code.Repository.Auth.AccountRepository;
import com.example.Code.Service.Auth.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
