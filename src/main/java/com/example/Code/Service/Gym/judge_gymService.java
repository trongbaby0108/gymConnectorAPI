package com.example.Code.Service.Gym;


import com.example.Code.Entity.Gym.gymRate;

import java.util.List;

public interface judge_gymService {
    public List<gymRate> getAll();
    public void save(gymRate judge_gym);
    public List<gymRate> getByGym(int id);
    public void delete(int id);
}
