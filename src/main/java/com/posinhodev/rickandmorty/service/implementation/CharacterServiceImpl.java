package com.posinhodev.rickandmorty.service.implementation;

import com.posinhodev.rickandmorty.model.Character;
import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import com.posinhodev.rickandmorty.model.dto.Result;
import com.posinhodev.rickandmorty.repository.CharacterRepository;
import com.posinhodev.rickandmorty.service.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @Autowired
    private CharacterRepository characterRepository;

    private static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";

    @Override
    public DataCharacters getAllCharacter() {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        }

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<DataCharacters> response = restTemplate.exchange(CHARACTER_API, HttpMethod.GET,
                entity, DataCharacters.class);

        log.info("INFO: FETCHING ALL CHARACTERS");

        saveCharactersToDatabase(response.getBody().getResults());

        return response.getBody();
    }

    private void saveCharactersToDatabase(List<Result> characters) {
        for (Result result : characters) {
            if (!characterRepository.existsByName(result.getName())) {
                Character character = new Character();
                character.setId(result.getId());
                character.setName(result.getName());
                character.setStatus(result.getStatus());
                character.setGender(result.getGender());
                character.setImage(result.getImage());
                characterRepository.save(character);
                log.info("INFO: Saved character with name {}", result.getName());
            } else {
                log.error("ERROR: Character with name {} already exists in the database", result.getName());
            }
        }
    }

    @Override
    public Page<Character> findAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        log.info("INFO: FETCHING CHARACTERS - PAGE {}, PAGE SIZE {}", page, pageSize);
        return characterRepository.findAll(pageRequest);
    }
}
