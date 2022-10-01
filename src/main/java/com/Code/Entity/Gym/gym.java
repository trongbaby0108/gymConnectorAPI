package com.Code.Entity.Gym;

import com.example.Code.Service.Gym.judge_gymService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gym")
public class gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "gymId")
    private int id ;
    private String name ;
    private String address ;
    private String phone ;
    private String email ;
    private String avatar;
    private boolean enable = true;
    
}
