package com.example.Code.Service.Gym;


import java.util.List;

import com.example.Code.Entity.Gym.combo;

public interface comboService{
    public List<combo> getAll();
    public void save(combo combo);
    public List<combo> getByGym(int id);
    public void delete(int id);
    public combo findByid(int id);
}
