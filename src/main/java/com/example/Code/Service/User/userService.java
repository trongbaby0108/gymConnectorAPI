package com.example.Code.Service.User;


import java.util.List;

import com.example.Code.Entity.User.user;

public interface userService {

    public List<user> getAll();
    public void save(user user);

    public user findByUserName(String username);

    public user findByName(String name);

    public user findById(int id);
}
