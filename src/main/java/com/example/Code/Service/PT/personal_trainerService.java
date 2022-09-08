package com.example.Code.Service.PT;

import com.example.Code.Entity.PT.personal_trainer;

import java.util.List;

public interface personal_trainerService {
    public List<personal_trainer> getAll();
    public void save(personal_trainer personal_trainer);

    public personal_trainer findById(int id);

    public List<personal_trainer> getPTByGym(int id);
    public personal_trainer findByUsername(String username);
}
