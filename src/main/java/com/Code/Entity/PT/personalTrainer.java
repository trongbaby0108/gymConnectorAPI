package com.Code.Entity.PT;

import com.Code.Entity.Auth.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class personalTrainer {
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
    private com.Code.Entity.Gym.gym gym;
}
