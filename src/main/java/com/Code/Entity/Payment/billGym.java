package com.Code.Entity.Payment;
import com.Code.Entity.Gym.combo;
import com.Code.Entity.Gym.gym;
import com.Code.Entity.User.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

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
