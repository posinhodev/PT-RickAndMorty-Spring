package com.posinhodev.rickandmorty.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class DataCharacters {
    List<Result> results;
}
