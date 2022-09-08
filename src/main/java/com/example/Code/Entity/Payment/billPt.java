package com.example.Code.Entity.Payment;
import com.example.Code.Entity.PT.personal_trainer;
import com.example.Code.Entity.User.user;
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
    private personal_trainer personal_trainer;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false, referencedColumnName = "userId")
    private user user;
}
