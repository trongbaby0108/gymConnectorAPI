package com.example.Code.Entity.PT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class picPT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "picPtId")
    private int id;
    private String img ;
    @ManyToOne
    @JoinColumn(name = "ptID",referencedColumnName = "ptId")
    private personalTrainer personal_trainer;
}
