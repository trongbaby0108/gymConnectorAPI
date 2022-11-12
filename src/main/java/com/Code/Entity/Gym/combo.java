package com.Code.Entity.Gym;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "comboId")
    private int id ;
    private String name ;
    private int price ;
    private boolean enable = true;
    @ManyToOne
    @JoinColumn(name = "gymId",nullable = false, referencedColumnName = "gymId")
    private gym gym;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
