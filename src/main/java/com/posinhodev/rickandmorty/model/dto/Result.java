package com.posinhodev.rickandmorty.model.dto;

import lombok.Data;

@Data
public class Result {
    private Integer id;
    private String name;
    private String status;
    private String gender;
    private String image;
}
