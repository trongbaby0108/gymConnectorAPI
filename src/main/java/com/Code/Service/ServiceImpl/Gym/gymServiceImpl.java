package com.Code.Service.ServiceImpl.Gym;

import com.Code.Entity.Gym.gym;
import com.Code.Exception.NotFoundException;
import com.Code.Service.Gym.gymService;
import com.Code.Repository.Gym.gymRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class gymServiceImpl implements gymService {

    private gymRepository gymRepo;

    public gymServiceImpl(gymRepository gymRepo) {
        this.gymRepo = gymRepo;
    }

    @Override
    public List<gym> getAll() {
        return gymRepo.findAll();
    }

    @Override
    public void signNewGym(gym gym) {
        gymRepo.save(gym);
    }

    @Override
    @SneakyThrows
    public gym findGymById(int id) {
        return gymRepo.findById(id).orElseThrow(() -> new NotFoundException("not found this gym"));
    }

    @Override
    public gym findGymByName(String name) {
        return gymRepo.findByName(name);
    }
}