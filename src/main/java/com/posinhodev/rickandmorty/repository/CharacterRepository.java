package com.posinhodev.rickandmorty.repository;

import com.posinhodev.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to consume and insert information into the database
 * @author posinhodev
 * @version 1.0
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    boolean existsByName(String name);

    Page<Character> findAll(Pageable pageable);

    Optional<Character> findByName(String name);
}
