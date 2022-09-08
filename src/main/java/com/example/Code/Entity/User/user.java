package com.example.Code.Entity.User;

import com.example.Code.Entity.Auth.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "userId")
    private int id ;
    private String name ;
    private String address;
    private String avatar;
    @OneToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountId")
    private Account account;
}
