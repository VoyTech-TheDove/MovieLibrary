package com.company;

import com.company.service.UserService;

import java.io.IOException;
import java.util.List;

public class UserGui {
    public static void userGui() throws MyException, IOException {
        FileUtils.createFileIfDoesntExist("users.dat");
        FileUtils.createFileIfDoesntExist("reviews.dat");
        List<User> users = FileUtils.readFromFileStream("users.dat");
        List<Movie> movies = FileUtils.readMoviesFromCSV();
        List<Review> reviews = FileUtils.readFromFileStream("reviews.dat");

        User loggedUser = UserService.loginOrRegisterSwitch(users);

        if (UserService.isAdmin(loggedUser)) {
            Menus.adminMenuSwitch(movies, reviews);
        } else if (UserService.isGuest(loggedUser)) {
            Menus.guestMenuSwitch(movies, reviews);
        } else {
            Menus.userMenuSwitch(movies, reviews, loggedUser);
        }
    }
}
