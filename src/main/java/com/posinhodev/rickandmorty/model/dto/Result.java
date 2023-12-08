package com.posinhodev.rickandmorty.model.dto;

import lombok.Data;

/**
 * DTO type class to obtain the necessary data from the api
 * @author posinhodev
 * @version 1.0
 */
@Data
public class Result {
    private Integer id;
    private String name;
    private String status;
    private String gender;
    private String image;
}
