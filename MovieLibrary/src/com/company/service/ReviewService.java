package com.company.service;

import com.company.*;

import java.util.Comparator;
import java.util.List;

public class ReviewService {
    public static void reviewMoviesByID(List<Review> reviewsParam, List<Movie> movies, User reviewAuthor) {
        int idFromInput = IOUtils.readIntFromUser("ID of the movie to be reviewed [\"0\" to cancel]");
        int inputReviewedMovieId;
        int inputGrade;
        String newReview;
        String inputReviewUser;
        try {
            if (idFromInput > 0) {
                Movie movieWithGivenId = MovieService.getMovieWithGivenId(movies, idFromInput);
                inputReviewedMovieId = movieWithGivenId.getId();
                do {
                    inputGrade = IOUtils.readIntFromUser("Grade of the movie [1 to 10]: \n[\"0\" to cancel]");
                } while (inputGrade > 10 || inputGrade < 0);
                if (inputGrade > 0) {
                    System.out.println("Write a short review [in one line]: ");
                    newReview = IOUtils.readStringFromUser("review");
                    inputReviewUser = reviewAuthor.getLogin();
                    reviewsParam.add(new Review(inputReviewedMovieId, inputGrade, inputReviewUser, newReview));
                }
            } else GuiUtils.incorrectCommand("reviewMoviesByID 2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showListOfReviews(List<Review> reviews, List<Movie> movies) {
        System.out.println("List of reviews:");
        for (Review result : reviews) {
            System.out.println(result.toString(movies));
        }
    }

    public static void showSortedListOfReviews(List<Review> reviews, List<Movie> movies) {
        reviews.sort(Comparator.comparing(Review::getId));
        showListOfReviews(reviews, movies);
    }

    public static void removeReviewWhenRemovingMovie(List<Review> reviews, int id) {
        for (Review review : reviews) {
            if (review.getId() == id) {
                reviews.remove(review);
            }
            System.out.println("review removed");
        }
    }
}
