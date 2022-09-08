package com.example.Code.Entity.PT;

import com.example.Code.Entity.User.user;
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
    private personal_trainer personal_trainer;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, referencedColumnName = "userId")
    private com.example.Code.Entity.User.user user;
}
