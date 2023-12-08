package com.posinhodev.rickandmorty.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Character")
@Table(name = "character")
public class Character {
    @Id
    private Integer Id;
    private String name;
    private String status;
    private String gender;
    private String image;
}
