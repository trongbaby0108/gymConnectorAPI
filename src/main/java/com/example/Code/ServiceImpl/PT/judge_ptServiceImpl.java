package com.example.Code.ServiceImpl.PT;

import com.example.Code.Entity.PT.ptRate;
import com.example.Code.Repository.PT.judge_ptRepository;
import com.example.Code.Service.PT.judge_ptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class judge_ptServiceImpl implements judge_ptService {
    @Autowired
    private judge_ptRepository judge_ptRepository;
    @Override
    public List<ptRate> getByPT(int id) {
        List<ptRate> res = new ArrayList<>();
        for(ptRate ptRate: judge_ptRepository.findAll()) {
            if(ptRate.getPersonalTrainer().getId() == id)
                res.add(ptRate);
        }
        return res;
    }

    @Override
    public void save(ptRate ptRate) {
        judge_ptRepository.save(ptRate);
    }
}
