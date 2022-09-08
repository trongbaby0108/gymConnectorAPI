package com.example.Code.Entity.Gym;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "comboId")
    private int id ;
    private String name ;
    private int price ;
    private boolean enable = true;
    @ManyToOne
    @JoinColumn(name = "gymId",nullable = false, referencedColumnName = "gymId")
    private gym gym;
}
