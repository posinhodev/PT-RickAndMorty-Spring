package com.posinhodev.rickandmorty.repository;

import com.posinhodev.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
