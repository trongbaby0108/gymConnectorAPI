package com.Code.Entity.PT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ptRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ptRateId")
    private int id;
    private String comment;
    private float vote;

    @ManyToOne
    @JoinColumn(name = "ptID",referencedColumnName = "ptId")
    private personalTrainer personalTrainer;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, referencedColumnName = "userId")
    private com.Code.Entity.User.user user;
}
