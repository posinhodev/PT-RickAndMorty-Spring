package com.posinhodev.rickandmorty.model.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO type class to manage the list of data obtained in the API
 * @author posinhodev
 * @version 1.0
 */
@Data
public class DataCharacters {
    List<Result> results;
}
