package com.Code.ServiceImpl.PT;

import com.Code.Entity.PT.ptRate;
import com.Code.Service.PT.judgePtService;
import com.Code.Repository.PT.judge_ptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class judgePtServiceImpl implements judgePtService {
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
