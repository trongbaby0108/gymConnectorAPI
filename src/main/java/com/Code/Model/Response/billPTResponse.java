package com.Code.Model.Response;

import com.Code.Entity.PT.personalTrainer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class billPTResponse {
    private int id ;
    private LocalDateTime dayStart ;
    private LocalDateTime dayEnd ;
    private personalTrainer trainer;
}
