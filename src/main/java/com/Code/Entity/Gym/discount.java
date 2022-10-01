package com.Code.Entity.Gym;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discount")
public class discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "discountId")
    private int id ;
    private int percent ;
    private Date Start;
    private Date End;
    private String content;

    @ManyToOne
    @JoinColumn(name = "gymId",nullable = false, referencedColumnName = "gymId")
    private gym gym;


}
