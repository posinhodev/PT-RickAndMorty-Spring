package com.posinhodev.rickandmorty.service.implementation;

import com.posinhodev.rickandmorty.model.dto.DataCharacters;
import com.posinhodev.rickandmorty.service.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

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

        return response.getBody();
    }
}
