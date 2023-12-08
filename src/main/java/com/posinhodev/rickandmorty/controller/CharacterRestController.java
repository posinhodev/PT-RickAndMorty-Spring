package com.posinhodev.rickandmorty.controller;

import com.posinhodev.rickandmorty.model.Character;
import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import com.posinhodev.rickandmorty.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController to expose APIs
 * @author posinhodev
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class CharacterRestController {

    @Autowired
    private CharacterService characterService;

    /**
     * Method to consume and store character information in the rick and morty api database
     * @return responseentity with the data
     */
    @GetMapping("/fetchData")
    public ResponseEntity<DataCharacters> getCharacters() {
        DataCharacters characters = characterService.getAllCharacter();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    /**
     * Method to list all characters with pagination
     * @param page page on which you want to be located
     * @param pageSize number of records to bring per page
     * @return responseentity with paginated data
     */
    @GetMapping("/listAll")
    public ResponseEntity<Page<Character>> getAllCharacterDb(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<Character> characters = characterService.findAll(page, pageSize);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    /**
     * method to save a new character in the database
     * @param character information to be saved
     * @return http status for saving the character
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveCharacter(@RequestBody Character character) {
        return characterService.create(character);
    }
}
