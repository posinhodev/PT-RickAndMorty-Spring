package com.posinhodev.rickandmorty.service;

import com.posinhodev.rickandmorty.model.Character;
import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * Service with the main methods that will be used
 * @author posinhodev
 * @version 1.0
 */
public interface CharacterService {
    ResponseEntity<String> create(Character character);

    DataCharacters getAllCharacter();

    Page<Character> findAll(int page, int pageSize);
}
