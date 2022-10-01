package com.Code.Entity.Payment;

import com.Code.Entity.PT.personalTrainer;
import com.Code.Entity.User.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class billPt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "billPtId")
    private int id ;
    private LocalDateTime dayStart ;
    private LocalDateTime dayEnd ;
    @ManyToOne
    @JoinColumn(name = "ptId",nullable = false, referencedColumnName = "ptId")
    private personalTrainer personal_trainer;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false, referencedColumnName = "userId")
    private user user;
}
