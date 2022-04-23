package com.arjios.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arjios.demo.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
