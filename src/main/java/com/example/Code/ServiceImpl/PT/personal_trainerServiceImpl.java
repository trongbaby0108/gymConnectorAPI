package com.example.Code.ServiceImpl.PT;

import com.example.Code.Entity.PT.personalTrainer;
import com.example.Code.Repository.PT.personal_trainerRepository;
import com.example.Code.Service.PT.personal_trainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class personal_trainerServiceImpl implements personal_trainerService {
    @Autowired
    private personal_trainerRepository personal_trainerRepository;
    @Override
    public List<personalTrainer> getAll() {
        return personal_trainerRepository.findAll();
    }

    @Override
    public void save(personalTrainer personal_trainer) {
        personal_trainerRepository.save(personal_trainer);
    }

    @Override
    public personalTrainer findById(int id) {
        return personal_trainerRepository.getById(id);
    }

    @Override
    public List<personalTrainer> getPTByGym(int id) {
        List<personalTrainer> res = new ArrayList<>();
        for (personalTrainer pt: personal_trainerRepository.findAll()) {
            if(pt.getGym().getId() == id ) res.add(pt);
        }
        return res;
    }

    @Override
    public personalTrainer findByUsername(String username) {
        for (personalTrainer pt: personal_trainerRepository.findAll()) {
            if(pt.getAccount().getUsername().equals(username)) return pt;
        }
        return new personalTrainer();
    }
}
