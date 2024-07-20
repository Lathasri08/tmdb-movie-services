package com.example.tmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tmdb.exception.InvalidDataException;
import com.example.tmdb.exception.NotFoundException;
import com.example.tmdb.model.Movie;
import com.example.tmdb.repo.MovieRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {
	
	@Autowired
	private MovieRepo movieRepository; 
	
	public Movie create(Movie movie) {
		
		if(movie==null) {
			throw new InvalidDataException("Invalid movie");
		}
		return movieRepository.save(movie);	
	}
	
	public Movie read(Long id) {
		
		return movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Movie not found"));
	}
	
	public void update(Long id, Movie updatedMovie) {
		
		if(updatedMovie==null || id==null) {
			throw new InvalidDataException("Invalid movie");
		}
		
		//movie exits or not
		if(movieRepository.existsById(id)) {
			Movie movie = movieRepository.getReferenceById(id);
			movie.setName(updatedMovie.getName());
			movie.setDirector(updatedMovie.getDirector());
			movie.setActors(updatedMovie.getActors());
			movieRepository.save(movie);
		}else {
			throw new NotFoundException("Movie not found");
		}
	}
	
	public void delete(Long id) {
		
		if(movieRepository.existsById(id)) {
			movieRepository.deleteById(id);
		}else {
			throw new NotFoundException("Movie not found");
		}
	}
}

















