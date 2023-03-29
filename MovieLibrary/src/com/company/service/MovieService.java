package com.company.service;

import com.company.IOUtils;
import com.company.Movie;
import com.company.Review;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public static void showList(List<Movie> movies) {
        System.out.println("List of movies:");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle() + " (" + movie.getDate() + ")");
        }
    }

    public static void detailedListMovies(List<Movie> movies) {
        System.out.println("A detailed list of movies:");
        System.out.println("-----------------------");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void moviesRemoveByID(List<Movie> movies, List<Review> reviews) {
        int idFromInput = IOUtils.readIntFromUser("ID of the movie to be removed [\"0\" to cancel]");
        try {
            if (idFromInput > 0) {
                Movie movieWithGivenId = getMovieWithGivenId(movies, idFromInput);
                movies.remove(movieWithGivenId);
                System.out.println("movie removed");
                ReviewService.removeReviewWhenRemovingMovie(reviews, idFromInput);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public boolean removeMovie(List<Movie> movies, int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                return true;
            }
        }
        return false;
    }

    public boolean remove(int id) {
        //dlaczego nie mogę zrobić .equals ?
        return movies.removeIf(movie -> (movie.getId()) == id);
    }

    public static Movie getMovieWithGivenId(List<Movie> movies, int idFromInput) {
        Movie movieWithGivenId = movies.stream().filter(movie -> movie.getId() == idFromInput).findFirst().orElseThrow();
        return movieWithGivenId;
    }

    public static void addMovie(List<Movie> movies) {
        int date;
        int id;
        String title = IOUtils.readStringFromUser("enter the title");
        do {
            date = IOUtils.readIntFromUser("enter the year");
            if (date < 1888) {
                System.out.println("try again... no movies are so old");
            }
        } while (date < 1888);
        if (movies.size() >= 1) {
            id = movies.get(movies.size() - 1).getId() + 1;
        } else {
            id = 1;
        }
        movies.add(new Movie(id, title, date));
        System.out.println("ID: " + id);
        System.out.println("Successfully added movie: " + movies.get(movies.size() - 1));
    }
}
