package com.x70s.sweetshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    private String code;

    @Column(length = 20)
    private String name;

//    @OneToMany(mappedBy = "authority")
//    private List<AppUser> appUsers;

}