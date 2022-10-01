package com.Code.Service.Gym;


import com.Code.Entity.Gym.gymRate;

import java.util.List;

public interface judgeGymService {
    public List<gymRate> getAll();
    public void save(gymRate judge_gym);
    public List<gymRate> getByGym(int id);
    public void delete(int id);
}
