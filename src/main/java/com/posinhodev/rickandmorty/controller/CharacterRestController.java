package com.posinhodev.rickandmorty.controller;

import com.posinhodev.rickandmorty.model.Character;
import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import com.posinhodev.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/characters")
public class CharacterRestController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/fetchData")
    public ResponseEntity<DataCharacters> getCharacters() {
        DataCharacters characters = characterService.getAllCharacter();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<Page<Character>> getAllCharacterDb(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<Character> characters = characterService.findAll(page, pageSize);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }
}
