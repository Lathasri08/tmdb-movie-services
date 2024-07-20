package com.example.tmdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tmdb.model.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long>{

}
