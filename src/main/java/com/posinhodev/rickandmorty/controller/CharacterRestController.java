package com.posinhodev.rickandmorty.controller;

import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import com.posinhodev.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/characters")
public class CharacterRestController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/list")
    public ResponseEntity<DataCharacters> getCharacters() {
        DataCharacters characters = characterService.getAllCharacter();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }
}
