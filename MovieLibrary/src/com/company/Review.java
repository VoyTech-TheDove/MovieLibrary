package com.company;

import com.company.service.MovieService;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable {

    private static final long serialVersionUID = -2856070926308430475l;

    private int id;
    private double grade;
    private String userLogin;
    private String reviewTxt;

    public Review(int id, double grade, String userLogin, String reviewTxt) {
        this.id = id;
        this.grade = grade;
        this.userLogin = userLogin;
        this.reviewTxt = reviewTxt;
    }

    public String toString(List<Movie> movies) {
        return "Review of " +
                MovieService.getMovieWithGivenId(movies, id).getTitle() +
                " by " + userLogin +
                "\ngrade = " + grade +
                "\n" + reviewTxt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGrade(double grade) {
        if (grade > 0 && grade <= 5) {
            this.grade = grade;
        } else {
            this.grade = 5;
            System.out.println("grade must be 1-5 -grade set to 5");
        }
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setReviewTxt(String reviewTxt) {
        this.reviewTxt = reviewTxt;
    }

    public int getId() {
        return id;
    }

    public double getGrade() {
        return grade;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getReviewTxt() {
        return reviewTxt;
    }
}
