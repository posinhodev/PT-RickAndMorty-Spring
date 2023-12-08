package com.posinhodev.rickandmorty.service;


import com.posinhodev.rickandmorty.model.Character;
import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface CharacterService {
    DataCharacters getAllCharacter();

    Page<Character> findAll(int page, int pageSize);
}
