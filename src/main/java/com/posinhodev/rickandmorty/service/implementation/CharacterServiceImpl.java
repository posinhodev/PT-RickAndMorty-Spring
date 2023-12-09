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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the service with the business logic to manage
 * the data of the Rick and Morty API
 * @author posinhodev
 * @version 1.0
 */
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

    /**
     * Method to consume data from the API and handling errors
     * @return information obtained from the API
     */
    @Override
    public DataCharacters getAllCharacter() {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        }

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        try {
            ResponseEntity<DataCharacters> response = restTemplate.exchange(
                    CHARACTER_API,
                    HttpMethod.GET,
                    entity,
                    DataCharacters.class);

            log.info("INFO: FETCHING CHARACTERS");

            DataCharacters dataCharacters = response.getBody();

            if (dataCharacters != null) {
                saveCharactersToDatabase(dataCharacters.getResults());
                return dataCharacters;
            } else {
                log.error("ERROR: Response body is null while fetching characters from the external API");
                throw new RuntimeException("Response body is null while fetching characters from the external API");
            }
        } catch (HttpClientErrorException e) {
            log.error("ERROR: fetching characters from the external API: {}", e.getMessage());
            throw new RuntimeException("Error fetching characters from the external API", e);
        } catch (Exception e) {
            log.error("ERROR: Unexpected error fetching characters from the external API: {}", e.getMessage());
            throw new RuntimeException("Unexpected error fetching characters from the external API", e);
        }
    }

    /**
     * Method to list all the characters found in the database with pagination
     * @param page page on which you want to be located
     * @param pageSize number of records to bring per page
     * @return characters with paginated
     */
    @Override
    public Page<Character> findAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        log.info("INFO: FETCHING CHARACTERS - PAGE {}, PAGE SIZE {}", page, pageSize);
        return characterRepository.findAll(pageRequest);
    }

    /**
     * Method to create a new character in the database
     * @param character character information that will be saved in the database
     * @return message informing whether the character was saved or not
     */
    @Override
    public ResponseEntity<String> create(Character character) {
        Optional<Character> existingCharacter = characterRepository.findByName(character.getName());

        if (existingCharacter.isPresent()) {
            log.error("ERROR: Character with name {} already exists in the database. Not saving again.", character.getName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Character already exists");
        } else {
            characterRepository.save(character);
            log.info("INFO: Saved character with name {}", character.getName());
            return ResponseEntity.ok("Character saved successfully");
        }
    }

    /**
     * Method to store the information obtained from the API in the database
     * @param characters api character information to save them in the database
     */
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
}
