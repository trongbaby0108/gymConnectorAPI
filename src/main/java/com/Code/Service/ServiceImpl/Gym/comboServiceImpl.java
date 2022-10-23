package com.Code.Service.ServiceImpl.Gym;

import java.util.ArrayList;
import java.util.List;

import com.Code.Entity.Gym.combo;
import com.Code.Service.Gym.comboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Code.Repository.Gym.comboRepository;

@Service
public class comboServiceImpl implements comboService {

    @Autowired
    private comboRepository comboRepository;

    @Override
    public List<combo> getAll() {
        return comboRepository.findAll();
    }

    @Override
    public void save(combo combo) {
        comboRepository.save(combo);
    }

    @Override
    public List<combo> getByGym(int id) {
        List<combo> combos = getAll();
        List<combo> result = new ArrayList<combo>();
        for (combo combo: combos) {
            if(combo.getGym().getId() == id)
                result.add(combo);
        }
        return result;
    }

    @Override
    public void delete(int id) {
        comboRepository.deleteById(id);
    }

    @Override
    public combo findByid(int id) {
        return comboRepository.findById(id).get();
    }
}
