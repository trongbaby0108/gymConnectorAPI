package com.Code.Entity.Gym;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gym")
public class gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gymId")
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String avatar;
    private boolean enable = true;

}
