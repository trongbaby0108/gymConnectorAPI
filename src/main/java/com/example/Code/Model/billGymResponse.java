package com.example.Code.Model;

import com.example.Code.Entity.Gym.combo;
import com.example.Code.Entity.Gym.gym;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class billGymResponse {
    private int id ;
    private LocalDateTime dayStart ;
    private LocalDateTime dayEnd ;
    private gym gym;
    private combo combo;
}
