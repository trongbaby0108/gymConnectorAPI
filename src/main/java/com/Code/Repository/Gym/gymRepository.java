package com.Code.Repository.Gym;

import com.Code.Entity.Gym.gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface gymRepository extends JpaRepository<gym,Integer> {
    public gym findByName(String name);
}
