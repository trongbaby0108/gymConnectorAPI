package com.Code.Entity.Gym;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class gymRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "gymRateId")
    private int id ;
    private String content ;
    private float vote ;
    @ManyToOne
    @JoinColumn(name = "gymId",nullable = false, referencedColumnName = "gymId")
    private gym gym;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false, referencedColumnName = "userId")
    private com.Code.Entity.User.user user;
}
