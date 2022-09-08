package com.example.Code.ServiceImpl.PT;

import com.example.Code.Entity.PT.personal_trainer;
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
    public List<personal_trainer> getAll() {
        return personal_trainerRepository.findAll();
    }

    @Override
    public void save(personal_trainer personal_trainer) {
        personal_trainerRepository.save(personal_trainer);
    }

    @Override
    public personal_trainer findById(int id) {
        return personal_trainerRepository.getById(id);
    }

    @Override
    public List<personal_trainer> getPTByGym(int id) {
        List<personal_trainer> res = new ArrayList<>();
        for (personal_trainer pt: personal_trainerRepository.findAll()) {
            if(pt.getGym().getId() == id ) res.add(pt);
        }
        return res;
    }

    @Override
    public personal_trainer findByUsername(String username) {
        for (personal_trainer pt: personal_trainerRepository.findAll()) {
            if(pt.getAccount().getUsername().equals(username)) return pt;
        }
        return new personal_trainer();
    }
}
