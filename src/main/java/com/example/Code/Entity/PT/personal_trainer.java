package com.example.Code.Entity.PT;

import com.example.Code.Entity.Auth.Account;
import com.example.Code.Entity.Gym.gym;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_trainer")
public class personal_trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ptId")
    private int id ;
    private String name;
    private String address;
    private String avatar;
    private int price;
    @OneToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "gymID", referencedColumnName = "gymId")
    private gym gym;
}
