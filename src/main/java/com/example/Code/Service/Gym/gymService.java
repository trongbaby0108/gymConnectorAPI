package com.example.Code.Service.Gym;

import com.example.Code.Entity.Gym.gym;

import java.util.List;

public interface gymService {
    public List<gym> getAll();
    public void signNewGym(gym gym);
    public gym findGymById(int id);
    public gym findGymByName(String name);
}
