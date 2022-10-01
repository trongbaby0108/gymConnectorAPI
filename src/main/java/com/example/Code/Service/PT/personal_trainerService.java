package com.example.Code.Service.PT;

import com.example.Code.Entity.PT.personalTrainer;

import java.util.List;

public interface personal_trainerService {
    public List<personalTrainer> getAll();
    public void save(personalTrainer personal_trainer);

    public personalTrainer findById(int id);

    public List<personalTrainer> getPTByGym(int id);
    public personalTrainer findByUsername(String username);
}
