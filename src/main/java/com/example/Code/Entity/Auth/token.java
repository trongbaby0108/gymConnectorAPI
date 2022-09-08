package com.example.Code.Entity.Auth;

import com.example.Code.Entity.Auth.Account;
import com.example.Code.Model.tokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class token {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id ;
    @Column(nullable = false)
    private String token ;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime expiryAt;
    private LocalDateTime confirmAt;
    private tokenType tokenType;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "accountId"
    )
    private Account account;
    public void genNewToken(){
        int min = 1000;
        int max = 9999;
        double rand = Math.random() * (max - min + 1) + min;
        this.token = String.valueOf((int) rand);
        this.createAt = LocalDateTime.now();
        this.expiryAt = LocalDateTime.now().plusMinutes(15);
    }
}
