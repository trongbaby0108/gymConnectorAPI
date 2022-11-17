package com.Code.Model.Response;

import com.Code.Entity.PT.personalTrainer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class billPTResponse {
    private int id ;
    private String dayStart ;
    private String dayEnd ;
    private personalTrainer trainer;
}
