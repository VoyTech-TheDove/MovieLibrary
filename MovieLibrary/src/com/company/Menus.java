package com.company;

import com.company.service.MovieService;
import com.company.service.ReviewService;

import java.io.IOException;
import java.util.List;

public class Menus {
    public static void userMenuSwitch(List<Movie> movies, List<Review> reviews, User user) throws IOException {
        int x = 0;
        while (x != 9) {
            GuiUtils.showUserMenu();
            x = IOUtils.readIntFromUser("option");
            switch (x) {
                case 1:
                    MovieService.detailedListMovies(movies);
                    break;
                case 2:
                    ReviewService.reviewMoviesByID(reviews, movies, user);
                    FileUtils.saveProgress(reviews, "reviews.dat");
                    break;
                case 3:
                    ReviewService.showListOfReviews(reviews,movies);
                    break;
                case 4:
                    ReviewService.showSortedListOfReviews(reviews,movies);
                case 9:
                    break;
                default:
                    System.out.println("Enter a number corresponding to selected action");
            }
        }
    }
    public static void guestMenuSwitch(List<Movie> movies, List<Review> reviews) throws IOException {
        int x;
        do {
            GuiUtils.showGuestMenu();
            x = IOUtils.readIntFromUser("option");
            switch (x) {
                case 1:
                    MovieService.detailedListMovies(movies);
                    break;
                case 3:
                    ReviewService.showListOfReviews(reviews,movies);
                    break;
                case 4:
                    ReviewService.showSortedListOfReviews(reviews,movies);
                case 9:
                    break;
                default:
                    System.out.println("Enter a number corresponding to selected action");
            }
        } while (x != 9);
    }
    public static void adminMenuSwitch(List<Movie> movies, List<Review>reviews) throws IOException {
        int x = 0;
        while(x!=9){
            GuiUtils.showAdminMenu();
            x = IOUtils.readIntFromUser("option:");
            switch (x) {
                case 1:
                    try {
                        MovieService.addMovie(movies);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Number of movies: ");
                    System.out.println(movies.size());
                    break;
                case 3:
                    MovieService.detailedListMovies(movies);
                    break;
                case 5:
                    MovieService.moviesRemoveByID(movies,reviews);
                    break;
                case 6:
                    MovieService.showList(movies);
                    break;
                case 7:
                    FileUtils.writeMoviesIntoCSV(movies);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Enter a number corresponding to selected action");
            }
        }
    }
}
