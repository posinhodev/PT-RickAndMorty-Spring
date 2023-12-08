package com.posinhodev.rickandmorty.repository;

import com.posinhodev.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    boolean existsByName(String name);

    Page<Character> findAll(Pageable pageable);
}
