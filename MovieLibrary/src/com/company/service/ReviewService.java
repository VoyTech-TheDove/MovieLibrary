package com.company.service;

import com.company.Movie;
import com.company.Review;
import com.company.User;

import java.util.Comparator;
import java.util.List;

import static com.company.IOUtils.readIntFromUser;
import static com.company.IOUtils.readStringFromUser;
import static com.company.service.MovieService.getMovieWithGivenId;
import static com.company.Utils.incorrectCommand;

public class ReviewService {

    public static void reviewMoviesByID(List<Review> reviewsParam, List<Movie> movies, List<User> users, User reviewAuthor) {
        int idFromInput = readIntFromUser("ID of the movie to be reviewed [\"0\" to cancel]");
        int inputReviewedMovieId;
        int inputGrade;
        String newReview;
        String inputReviewUser;
        try {
            if (idFromInput > 0) {
                Movie movieWithGivenId = getMovieWithGivenId(movies, idFromInput);
                inputReviewedMovieId = movieWithGivenId.getId();
                do {
                    inputGrade = readIntFromUser("Grade of the movie [1 to 10]: \n[\"0\" to cancel]");
                } while (inputGrade > 10 || inputGrade < 0);
                if (inputGrade > 0) {

                    System.out.println("Write a short review [in one line]: ");
                    newReview = readStringFromUser("review");
                    inputReviewUser = reviewAuthor.getLogin();
                    reviewsParam.add(new Review(inputReviewedMovieId, inputGrade, inputReviewUser, newReview));
                }
            } else
                incorrectCommand("reviewMoviesByID 2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showListOfReviews(List<Review> reviews, List<Movie> movies) {
        System.out.println("List of reviews:");
        for (Review result : reviews)
            System.out.println(result.toString(movies));
    }

    public static void showSortedListOfReviews(List<Review> reviews, List<Movie> movies) {
        reviews.sort(Comparator.comparing(Review::getId));
        showListOfReviews(reviews,movies);
    }
/*
    public static void showSortedListOfReviews(List<Review> reviews, List<Movie> movies) {
        System.out.println("List of reviews:");
        reviews.sort(Comparator.comparing(Review::getId));
        for (Review result : reviews)
            System.out.println(result.toString(movies));
    }
    */
    public static void removeReviewWhenRemovingMovie(List<Review> reviews, int id) {
        for (Review review : reviews)
            if (review.getId() == id) {
                reviews.remove(review);
            }
        System.out.println("review removed");

    }
}
