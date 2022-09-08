package com.example.Code.Service.Gym;


import java.util.List;

import com.example.Code.Entity.Gym.discount;

public interface discountService {
    public List<discount> getAll();
    public void save(discount discount);
    public List<discount> getByGym(int id);
    public void delete(int id);
}
