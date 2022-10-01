package com.example.Code.Entity.Payment;
import com.example.Code.Entity.Gym.combo;
import com.example.Code.Entity.Gym.gym;
import com.example.Code.Entity.User.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table
public class billGym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "billGymID")
    private int id ;
    private LocalDateTime dayStart ;
    private LocalDateTime dayEnd ;

    @ManyToOne
    @JoinColumn(name = "gymId",nullable = false, referencedColumnName = "gymId")
    private gym gym;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false, referencedColumnName = "userId")
    private user user;

    @ManyToOne
    @JoinColumn(name = "comboId",nullable = false, referencedColumnName = "comboId")
    private combo combo;
}
