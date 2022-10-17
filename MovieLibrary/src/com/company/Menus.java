package com.company;

import java.io.IOException;
import java.util.List;


import static com.company.FileUtils.csvWriter;
import static com.company.FileUtils.saveProgress;
import static com.company.IOUtils.readIntFromUser;
import static com.company.Utils.*;
import static com.company.service.MovieService.*;
import static com.company.service.ReviewService.*;

public class Menus {


    public static void userMenuSwitch(List<Movie> movies, List<User> users, List<Review> reviews, User user) throws IOException {
        int x = 0;
        while (x != 9) {
            showUserMenu();
            x = readIntFromUser("option");
            switch (x) {
                case 1:
                    detailedListMovies(movies);
                    break;
                case 2:
                    reviewMoviesByID(reviews, movies, users, user);
                    saveProgress(reviews, "reviews.dat");
                    break;
                case 3:
                    showListOfReviews(reviews,movies);
                    break;
                case 4:
                    showSortedListOfReviews(reviews,movies);
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
            showGuestMenu();
            x = readIntFromUser("option");
            switch (x) {
                case 1:
                    detailedListMovies(movies);
                    break;
                case 3:
                    showListOfReviews(reviews,movies);
                    break;
                case 4:
                    showSortedListOfReviews(reviews,movies);
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
            showAdminMenu();
            x = readIntFromUser("option:");
            switch (x) {
                case 1:
                    try {
                        addMovie(movies);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Number of movies: ");
                    System.out.println(movies.size());
                    break;
                case 3:
                    detailedListMovies(movies);
                    break;
                case 5:
                    moviesRemoveByID(movies,reviews);
                    break;
                case 6:
                    showList(movies);
                    break;
                case 7:
                    csvWriter(movies);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Enter a number corresponding to selected action");
            }
        }
    }
}
