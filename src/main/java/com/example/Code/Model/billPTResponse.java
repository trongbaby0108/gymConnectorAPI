package com.example.Code.Model;

import com.example.Code.Entity.PT.personalTrainer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class billPTResponse {
    private int id ;
    private LocalDateTime dayStart ;
    private LocalDateTime dayEnd ;
    private personalTrainer trainer;
}
