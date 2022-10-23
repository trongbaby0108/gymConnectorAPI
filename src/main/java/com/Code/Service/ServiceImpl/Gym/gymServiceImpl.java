package com.Code.ServiceImpl.Gym;

import com.Code.Entity.Gym.gym;
import com.Code.Service.Gym.gymService;
import com.Code.Repository.Gym.gymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class gymServiceImpl implements gymService {

    @Autowired
    private gymRepository gymRepo;

    @Override
    public List<gym> getAll() {
        return gymRepo.findAll();
    }

    @Override
    public void signNewGym(gym gym) {
        gymRepo.save(gym);
    }

    @Override
    public gym findGymById(int id) {
        return gymRepo.findById(id).get();
    }

    @Override
    public gym findGymByName(String name) {
        return gymRepo.findByName(name);
    }
}
