package com.example.Code.Repository.Gym;

import com.example.Code.Entity.Gym.gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface gymRepository extends JpaRepository<gym,Integer> {
    public gym findByName(String name);
}
