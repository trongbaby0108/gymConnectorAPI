package com.Code.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class billGymResponse {
    private int id ;
    private LocalDateTime dayStart ;
    private LocalDateTime dayEnd ;
    private com.Code.Entity.Gym.gym gym;
    private com.Code.Entity.Gym.combo combo;
}
