package com.movie.api.service;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@PropertySource("/application.properties")
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public Movie getMovieById(Long movieId) {
        log.debug("##### ServiceImpl *** getMovieById *** MovieID=" + movieId + " ######");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.themoviedb.org/3/movie/" + movieId + "?api_key="+apiKey;
        Movie movie = restTemplate.getForObject(url, Movie.class);
        movie.setMovie_id(movieId);
        log.debug("Movie " + movie.getMovie_id() + "  " + movie.getOriginal_title());
        return movie;
    }

    /*@Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }*/

    //@Override
    //public Movie addMovie(Movie movie) {
    //  return movieRepository.save(movie);
    //}

    //@Override
    //public Movie updateMovieById(Long id, Movie movie) {
    //  return null;
    //}
}
