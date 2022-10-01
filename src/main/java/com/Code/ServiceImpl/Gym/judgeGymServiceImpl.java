package com.Code.ServiceImpl.Gym;


import com.Code.Entity.Gym.gymRate;
import com.Code.Service.Gym.judgeGymService;
import com.Code.Repository.Gym.judge_gymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class judgeGymServiceImpl implements judgeGymService {

    @Autowired
    private judge_gymRepository judge_gymRepository;

    @Override
    public List<gymRate> getAll() {
        return judge_gymRepository.findAll();
    }

    @Override
    public void save(gymRate judge_gym) {
        judge_gymRepository.save(judge_gym);
    }

    @Override
    public List<gymRate> getByGym(int id) {
        List<gymRate> judge_gyms = getAll();
        List<gymRate> result = new ArrayList<gymRate>();
        for (gymRate judge_gym: judge_gyms) {
            if(judge_gym.getGym().getId() == id)
                result.add(judge_gym);
        }
        return result;
    }

    @Override
    public void delete(int id) {
        judge_gymRepository.deleteById(id);
    }
}
